package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import pl.javaskills.creditapp.core.model.LoanApplication;

import java.math.BigDecimal;
import java.util.UUID;

public class CreditApplicationService {
    private static final Logger log = LoggerFactory.getLogger(CreditApplicationService.class);
    private final PersonScoringCalculator personScoringCalculator;
    private final CreditRatingCalculator calculator;

    public CreditApplicationService(PersonScoringCalculator personScoringCalculator, CreditRatingCalculator calculator) {
        this.personScoringCalculator = personScoringCalculator;
        this.calculator = calculator;
    }

    public CreditApplicationDecision getDecision(LoanApplication loanApplication){
        String id = UUID.randomUUID().toString();
        log.info("Application ID is "+id);
        MDC.put("id", id);
        int score = personScoringCalculator.calculate(loanApplication.getPerson());
        DecisionType decisionType;
        if (score < 300){
            decisionType = DecisionType.NEGATIVE_SCORING;
        }
        else if (score >= 300 && score <=400){
            decisionType = DecisionType.CONTACT_REQUIRED;
        }
        else{
            double creditRating = calculator.getCreditRating(loanApplication);
            double amount = loanApplication.getPurposeOfLoan().getAmount();
            if (score > 400 && creditRating>=amount){
                if (amount > Constants.MIN_LOAN_AMOUNT_MORTGAGE)
                decisionType = DecisionType.POSITIVE;
                else decisionType = DecisionType.NEGATIVE_REQUIREMENTS_NOT_MET;
            }
            else {
                BigDecimal roundedCreditRating = new BigDecimal(creditRating).setScale(2);
                decisionType = DecisionType.NEGATIVE_CREDIT_RATING;
                decisionType.setCreditRating(roundedCreditRating);
            }
        }
        log.info("Decision = "+decisionType);
        return new CreditApplicationDecision(decisionType, loanApplication.getPerson().getPersonalData());
    }
}