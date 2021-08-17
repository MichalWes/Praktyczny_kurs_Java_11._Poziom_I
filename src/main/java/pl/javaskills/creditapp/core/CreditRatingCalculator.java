package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.LoanApplication;

import java.math.BigDecimal;

public class CreditRatingCalculator {
    private static final Logger log = LoggerFactory.getLogger(CreditRatingCalculator.class);
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
        log.info("Calculated rating = "+new BigDecimal(creditRating).setScale(2));
        return creditRating;
    }
}
