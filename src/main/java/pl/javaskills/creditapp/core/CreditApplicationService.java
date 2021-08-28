package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import pl.javaskills.creditapp.core.model.LoanApplication;
import java.util.UUID;
import static pl.javaskills.creditapp.core.Constants.*;
import static pl.javaskills.creditapp.core.DecisionType.*;

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
        double creditRating = calculator.getCreditRating(loanApplication);
        DecisionType decisionType ;

        if (score < 300){
            decisionType = NEGATIVE_SCORING;
        }
        else if (score >= 300 && score <=400){
            decisionType = CONTACT_REQUIRED;
        }
        else{

            double amount = loanApplication.getPurposeOfLoan().getAmount();

            if (score > 400 && creditRating>=amount){
                if (amount > MIN_LOAN_AMOUNT_MORTGAGE)
                decisionType = POSITIVE;
                else {
                    decisionType = NEGATIVE_REQUIREMENTS_NOT_MET;
                }
            }
            else {
                decisionType = NEGATIVE_CREDIT_RATING;
            }
        }
        log.info("Decision = "+decisionType);
        return new CreditApplicationDecision(decisionType, loanApplication.getPerson().getPersonalData(), creditRating, score);
    }
}