package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.LoanApplication;
import pl.javaskills.creditapp.core.model.Person;

import java.math.BigDecimal;

public class CreditApplicationService {
    private final PersonScoringCalculator calculator;

    public CreditApplicationService(PersonScoringCalculator calculator) {
        this.calculator = calculator;
    }

    public DecisionType getDecision(LoanApplication loanApplication){
        int score = calculator.calculate(loanApplication.getPerson());

        DecisionType decision;
        if (score < 300){
            decision = DecisionType.NEGATIVE_SCORING;
        }
        else if (score >= 300 && score <=400){
            decision = DecisionType.CONTACT_REQUIRED;
        }
        else{
            double creditRating = loanApplication.getPerson().getIncomePerFamilyMember() * 12 * loanApplication.getPurposeOfLoan().getPeriod();
            switch (loanApplication.getPurposeOfLoan().getType()) {
                case PERSONAL_LOAN:
                    creditRating = creditRating * Constants.PERSONAL_LOAN_LOAN_RATE;
                    break;
                case MORTGAGE:
                    creditRating = creditRating * Constants.MORTGAGE_LOAN_RATE;
                    break;
            }
            if (score > 400 && creditRating>=loanApplication.getPurposeOfLoan().getAmount()){
               decision = DecisionType.POSITIVE;
            }
            else {
                BigDecimal roundedCreditRating = new BigDecimal(creditRating).setScale(2);
                decision = DecisionType.NEGATIVE_CREDIT_RATING;
                decision.setCreditRating(roundedCreditRating);
            }
        }
        return decision;
    }

}