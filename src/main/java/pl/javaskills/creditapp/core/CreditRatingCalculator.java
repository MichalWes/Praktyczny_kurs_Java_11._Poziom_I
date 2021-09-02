package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.CreditApplication;
import static pl.javaskills.creditapp.core.Constants.*;

import java.math.BigDecimal;

public class CreditRatingCalculator {
    private static final Logger log = LoggerFactory.getLogger(CreditRatingCalculator.class);
    public double getCreditRating(CreditApplication creditApplication) {

        double creditRating = creditApplication.getPerson().getIncomePerFamilyMember() * 12 * creditApplication.getPurposeOfLoan().getPeriod();
        switch (creditApplication.getPurposeOfLoan().getType()) {
            case PERSONAL_LOAN:
                creditRating = creditRating * PERSONAL_LOAN_LOAN_RATE;
                break;
            case MORTGAGE:
                creditRating = creditRating * MORTGAGE_LOAN_RATE;
                break;
        }
        log.info("Calculated rating = "+new BigDecimal(creditRating).setScale(2));
        return creditRating;
    }
}
