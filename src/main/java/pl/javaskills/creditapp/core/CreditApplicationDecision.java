package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.exception.RequirementNotMetCause;
import pl.javaskills.creditapp.core.model.PersonalData;
import pl.javaskills.creditapp.di.Inject;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static pl.javaskills.creditapp.core.Constants.MIN_LOAN_AMOUNT_MORTGAGE;

public class CreditApplicationDecision {
    private final DecisionType decisionType;
    private final Optional<RequirementNotMetCause> requirementNotMetCause;
    @Inject
    private PersonalData personalData;
    private final Double creditRating;
    private final int scoring;
    Locale locale;
    ResourceBundle resourceBundle;

    public CreditApplicationDecision(DecisionType decisionType, PersonalData personalData, Double creditRating, int scoring, Locale locale) {
        this.decisionType = decisionType;
        this.personalData = personalData;
        this.creditRating = creditRating;
        this.scoring = scoring;
        this.requirementNotMetCause = Optional.empty();
        this.locale = locale;
        this.resourceBundle = ResourceBundle.getBundle("translations", locale);
    }

    public CreditApplicationDecision(DecisionType decisionType, PersonalData personalData, Double creditRating, int scoring, RequirementNotMetCause cause, Locale locale) {
        this.decisionType = decisionType;
        this.personalData = personalData;
        this.creditRating = creditRating;
        this.scoring = scoring;
        this.requirementNotMetCause = Optional.of(cause);
        this.locale = locale;
    }

    public DecisionType getDecisionType() {
        return decisionType;
    }

    public Double getCreditRating() {
        return creditRating;
    }

    public int getScoring() {
        return scoring;
    }

    public String getDecisionString() {
        switch (decisionType) {
            case NEGATIVE_SCORING:
                return resourceBundle.getString("sorry") + " " + personalData.getName() + " " + personalData.getLastName() + " " + resourceBundle.getString("decisionNegative");
            case CONTACT_REQUIRED:
                return resourceBundle.getString("sorry") + " " + personalData.getName() + " " + personalData.getLastName() + " " + resourceBundle.getString("additionalDocuments");
            case POSITIVE:
                return resourceBundle.getString("congratulations") + " " + personalData.getName() + " " + personalData.getLastName() + " " + resourceBundle.getString("decisionPositive");
            case NEGATIVE_CREDIT_RATING:
                return resourceBundle.getString("sorry") + " " + personalData.getName() + " " + personalData.getLastName() + resourceBundle.getString("decisionNegativeLimit") + " " + new BigDecimal(creditRating).setScale(2);
            case NEGATIVE_REQUIREMENTS_NOT_MET:
                switch (requirementNotMetCause.get()) {
                    case TOO_LOW_LOAN_AMOUNT:
                        return resourceBundle.getString("sorry") + " " + personalData.getName() + " " + personalData.getLastName() + resourceBundle.getString("decisionNegativeMortgage")+ " " + MIN_LOAN_AMOUNT_MORTGAGE;
                    case TOO_HIGH_PERSONAL_EXPENSES:
                        return resourceBundle.getString("sorry") + " " + personalData.getName() + " " + personalData.getLastName() + resourceBundle.getString("decisionNegativeHighExpenses");
                }

        }
        return null;
    }
}
