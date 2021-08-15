package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.LoanApplication;

public class CreditApplicationService {
    public String getDecision(LoanApplication loanApplication){
        int score = new PersonScoringCalculator().calculate(loanApplication.getPerson());


        String decision="";
        if (score < 300){
            decision = "Sorry " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + " decision is negative.";
        }
        else if (score >= 300 && score <=400){
            decision = "Sorry " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", bank requires additional documents. Our Consultant will contact you.";
        }
        else{
            double creditRating = loanApplication.getPerson().getIncomePerFamilyMember() * 12 * loanApplication.getPurposeOfLoan().getPeriod();
            switch (loanApplication.getPurposeOfLoan().getType()) {
                case PERSONAL:
                    creditRating = creditRating * Constants.PERSONAL_LOAN_LOAN_RATE;
                    break;
                case MORTGAGE:
                    creditRating = creditRating * Constants.MORTGAGE_LOAN_RATE;
                    break;
            }
            if (score > 400 && creditRating>=loanApplication.getPurposeOfLoan().getAmount()){
                decision = "Congratulations, " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", decision is positive.";
            }
            else {
                decision = "Sorry, " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", decision is negative. Bank can borrow only " + creditRating;
            }
        }
        return decision;
    }

}