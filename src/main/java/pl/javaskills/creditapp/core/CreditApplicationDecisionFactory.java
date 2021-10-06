package pl.javaskills.creditapp.core;


import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.PersonalData;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static pl.javaskills.creditapp.core.Constants.MIN_LOAN_AMOUNT_MORTGAGE;


public class CreditApplicationDecisionFactory {


    public String getDecisionString(CreditApplicationDecision creditApplicationDecision, CreditApplication creditApplication) {
        Locale locale = creditApplication.getClientLocale();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("translations", locale);
        PersonalData personalData = creditApplication.getPerson().getPersonalData();
        NumberFormat numberFormat = NumberFormat.getNumberInstance(creditApplication.getClientLocale());

        switch (creditApplicationDecision.getDecisionType()) {
            case NEGATIVE_SCORING:
                return String.format(resourceBundle.getString("decisionNegative"), personalData.getName(), personalData.getLastName());
            case CONTACT_REQUIRED:
                return String.format(resourceBundle.getString("additionalDocuments"), personalData.getName(), personalData.getLastName());
            case POSITIVE:
                return String.format(resourceBundle.getString("decisionPositive"), personalData.getName(), personalData.getLastName());
            case NEGATIVE_CREDIT_RATING:
                BigDecimal bdRating = new BigDecimal(creditApplicationDecision.getCreditRating()).setScale(2);
                return String.format(resourceBundle.getString("decisionNegativeLimit"), personalData.getName(), personalData.getLastName(), numberFormat.format(bdRating.doubleValue()));
            case NEGATIVE_REQUIREMENTS_NOT_MET:
                switch (creditApplicationDecision.getRequirementNotMetCause().get()) {
                    case TOO_LOW_LOAN_AMOUNT:
                        return String.format(resourceBundle.getString("decisionNegativeMortgage"), personalData.getName(), personalData.getLastName(), numberFormat.format(MIN_LOAN_AMOUNT_MORTGAGE));
                    case TOO_HIGH_PERSONAL_EXPENSES:
                        return String.format(resourceBundle.getString("decisionNegativeHighExpenses"), personalData.getName(), personalData.getLastName());
                }

        }
        return null;
    }

}
