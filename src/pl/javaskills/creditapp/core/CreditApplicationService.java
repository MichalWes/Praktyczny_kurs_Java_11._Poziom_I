package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.LoanApplication;

public class CreditApplicationService {
    public String getDecision(LoanApplication loanApplication){
        int score = new PersonScoringCalculator().calculate(loanApplication.getPerson());
        double earningpp = loanApplication.getPerson().getPersonalData().getTotalMonthlyIncomePLN()/loanApplication.getPerson().getPersonalData().getNumOfFamilyDependants();
        double creditRating = loanApplication.getPurposeOfLoan().getType().getRate() * earningpp * 12 * loanApplication.getPurposeOfLoan().getPeriod();

        int result = 0;
        if (score < 300){
            result = 1;
        }
        else if (score >= 300 && score <=400){
            result = 2;
        }
        else if (score > 400 && creditRating>=loanApplication.getPurposeOfLoan().getAmount()){
            result = 3;
        }
        else if(score > 400 && creditRating<loanApplication.getPurposeOfLoan().getAmount()){
            result = 4;
        }


        String decision ="";

        switch (result){
            case 1:
                decision = "Sorry " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + " decision is negative.";
                break;
        }
        switch (result){
            case 2:
                decision = "Sorry " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", bank requires additional documents. Our Consultant will contact you.";
                break;
        }
        switch (result){
            case 3:
                decision = "Congratulations, " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", decision is positive.";
                break;
        }
        switch (result){
            case 4:
                decision = "Sorry, " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", decision is negative. Bank can borrow only " + creditRating;
                break;
        }
        return decision;
    }

}