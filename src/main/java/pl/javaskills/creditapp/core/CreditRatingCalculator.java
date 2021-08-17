package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.LoanApplication;

public class CreditRatingCalculator {
    public double getCreditRating(LoanApplication loanApplication) {
        double creditRating = loanApplication.getPerson().getIncomePerFamilyMember() * 12 * loanApplication.getPurposeOfLoan().getPeriod();
        switch (loanApplication.getPurposeOfLoan().getType()) {
            case PERSONAL_LOAN:
                creditRating = creditRating * Constants.PERSONAL_LOAN_LOAN_RATE;
                break;
            case MORTGAGE:
                creditRating = creditRating * Constants.MORTGAGE_LOAN_RATE;
                break;
        }
        return creditRating;
    }
}
