package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.validation.CreditApplicationValidator;

import static pl.javaskills.creditapp.core.Constants.*;
import static pl.javaskills.creditapp.core.DecisionType.*;

public class CreditApplicationService {
    private static final Logger log = LoggerFactory.getLogger(CreditApplicationService.class);
    private final PersonScoringCalculatorFactory personScoringCalculatorFactory;
    private final CreditRatingCalculator calculator;
    private final CreditApplicationValidator creditApplicationValidator;


    public CreditApplicationService(PersonScoringCalculatorFactory personScoringCalculatorFactory, CreditRatingCalculator calculator, CreditApplicationValidator creditApplicationValidator) {
        this.personScoringCalculatorFactory = personScoringCalculatorFactory;
        this.calculator = calculator;
        this.creditApplicationValidator = creditApplicationValidator;
    }

    public CreditApplicationDecision getDecision(CreditApplication creditApplication) {
        String id = creditApplication.getId().toString();
        MDC.put("id", id);
        try {
            creditApplicationValidator.validate(creditApplication);

            Person person = creditApplication.getPerson();
            int score = personScoringCalculatorFactory.getCalculator(person).calculate(person);
            double creditRating = calculator.getCreditRating(creditApplication);
            DecisionType decisionType;

            if (score < 300) {
                decisionType = NEGATIVE_SCORING;
            } else if (score >= 300 && score <= 400) {
                decisionType = CONTACT_REQUIRED;
            } else {

                double amount = creditApplication.getPurposeOfLoan().getAmount();

                if (score > 400 && creditRating >= amount) {
                    if (amount > MIN_LOAN_AMOUNT_MORTGAGE)
                        decisionType = POSITIVE;
                    else {
                        decisionType = NEGATIVE_REQUIREMENTS_NOT_MET;
                    }
                } else {
                    decisionType = NEGATIVE_CREDIT_RATING;
                }
            }
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
}