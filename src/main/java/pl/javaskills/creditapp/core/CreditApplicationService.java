package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.LoanApplication;

import java.math.BigDecimal;

public class CreditApplicationService {
    private final PersonScoringCalculator personScoringCalculator;
    private final CreditRatingCalculator calculator;

    public CreditApplicationService(PersonScoringCalculator personScoringCalculator, CreditRatingCalculator calculator) {
        this.personScoringCalculator = personScoringCalculator;
        this.calculator = calculator;
    }

    public CreditApplicationDecision getDecision(LoanApplication loanApplication){
        int score = personScoringCalculator.calculate(loanApplication.getPerson());

        DecisionType decision;
        if (score < 300){
            return new CreditApplicationDecision(DecisionType.NEGATIVE_SCORING, loanApplication.getPerson().getPersonalData());
        }
        else if (score >= 300 && score <=400){
            return new CreditApplicationDecision(DecisionType.CONTACT_REQUIRED, loanApplication.getPerson().getPersonalData());
        }
        else{
            double creditRating = calculator.getCreditRating(loanApplication);
            if (score > 400 && creditRating>=loanApplication.getPurposeOfLoan().getAmount()){
                return new CreditApplicationDecision(DecisionType.POSITIVE, loanApplication.getPerson().getPersonalData());
            }
            else {
                BigDecimal roundedCreditRating = new BigDecimal(creditRating).setScale(2);
                decision = DecisionType.NEGATIVE_CREDIT_RATING;
                decision.setCreditRating(roundedCreditRating);
                return new CreditApplicationDecision(decision, loanApplication.getPerson().getPersonalData());
            }
        }

    }
}