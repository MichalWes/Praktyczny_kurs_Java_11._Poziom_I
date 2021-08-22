package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.PersonalData;

public class CreditApplicationDecision {
    private final DecisionType decisionType;
    private final PersonalData personalData;

    public CreditApplicationDecision(DecisionType decisionType, PersonalData personalData) {
        this.decisionType = decisionType;
        this.personalData = personalData;
    }

    public DecisionType getDecisionType() {
        return decisionType;
    }

    public String getDecisionString() {
        switch (decisionType) {
            case NEGATIVE_SCORING:
                return "Sorry " + personalData.getName() + " " + personalData.getLastName() + " decision is negative.";
            case CONTACT_REQUIRED:
                return "Sorry " + personalData.getName() + " " + personalData.getLastName() + ", bank requires additional documents. Our Consultant will contact you.";
            case POSITIVE:
                return "Congratulations, " + personalData.getName() + " " + personalData.getLastName() + ", decision is positive.";
            case NEGATIVE_CREDIT_RATING:
                return "Sorry, " + personalData.getName() + " " + personalData.getLastName() + ", decision is negative. Bank can borrow only " + decisionType.getCreditRating();
            case NEGATIVE_REQUIREMENTS_NOT_MET:
                return "Sorry, " + personalData.getName() + " " + personalData.getLastName() + ", decision is negative. Minimum loan amount for mortgage is: "+Constants.MIN_LOAN_AMOUNT_MORTGAGE;
        }
        return null;
    }
}
