package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import pl.javaskills.creditapp.core.exception.RequirementNotMetCause;
import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.validation.CompoundPostValidator;
import pl.javaskills.creditapp.core.validation.CreditApplicationValidator;

import static pl.javaskills.creditapp.core.Constants.*;
import static pl.javaskills.creditapp.core.DecisionType.*;

public class CreditApplicationService {
    private static final Logger log = LoggerFactory.getLogger(CreditApplicationService.class);
    private final PersonScoringCalculatorFactory personScoringCalculatorFactory;
    private final CreditRatingCalculator calculator;
    private final CreditApplicationValidator creditApplicationValidator;
    private final CompoundPostValidator compoundPostValidator;


    public CreditApplicationService(PersonScoringCalculatorFactory personScoringCalculatorFactory, CreditRatingCalculator calculator, CreditApplicationValidator creditApplicationValidator, CompoundPostValidator compoundPostValidator) {
        this.personScoringCalculatorFactory = personScoringCalculatorFactory;
        this.calculator = calculator;
        this.creditApplicationValidator = creditApplicationValidator;
        this.compoundPostValidator = compoundPostValidator;
    }

    public CreditApplicationDecision getDecision(CreditApplication creditApplication) {
        String id = creditApplication.getId().toString();
        MDC.put("id", id);
        try {
            //step 1
            creditApplicationValidator.validate(creditApplication);

            Person person = creditApplication.getPerson();
            //step 2
            int score = personScoringCalculatorFactory.getCalculator(person).calculate(creditApplication);
            //step 3
            double creditRating = calculator.getCreditRating(creditApplication);
            //step 4
            try {
                compoundPostValidator.validate(creditApplication, score, creditRating);
            } catch (RequirementNotMetException ex) {
                return new CreditApplicationDecision(NEGATIVE_REQUIREMENTS_NOT_MET, creditApplication.getPerson().getPersonalData(), creditRating, score, ex.getRequirementNotMentCause());
            }
            DecisionType decisionType = getDecisionType(creditApplication, score, creditRating);
            log.info("Decision = " + decisionType);
            return new CreditApplicationDecision(decisionType, creditApplication.getPerson().getPersonalData(), creditRating, score);
        } catch (ValidationException validationException) {
            log.error(validationException.getMessage());
            throw new IllegalStateException();
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new IllegalStateException();
        } finally {
            log.info("Application processing is finished");
        }
    }

    private DecisionType getDecisionType(CreditApplication creditApplication, int score, double creditRating) {
        DecisionType decisionType;

        if (score < 300) {
            decisionType = NEGATIVE_SCORING;
        } else if (score >= 300 && score <= 400) {
            decisionType = CONTACT_REQUIRED;
        } else {
            double amount = creditApplication.getPurposeOfLoan().getAmount();
            if (creditRating >= amount) {
                decisionType = POSITIVE;
            } else {
                decisionType = NEGATIVE_CREDIT_RATING;
            }
        }
        return decisionType;
    }
}