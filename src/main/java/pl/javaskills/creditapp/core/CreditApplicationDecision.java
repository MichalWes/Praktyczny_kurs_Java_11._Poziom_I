package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.PersonalData;
import static pl.javaskills.creditapp.core.Constants.*;
import java.math.BigDecimal;

public class CreditApplicationDecision {
    private final DecisionType decisionType;
    private final PersonalData personalData;
    private final Double creditRating;
    private final int scoring;

    public CreditApplicationDecision(DecisionType decisionType, PersonalData personalData, Double creditRating, int scoring) {
        this.decisionType = decisionType;
        this.personalData = personalData;
        this.creditRating = creditRating;
        this.scoring = scoring;
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
                return "Sorry " + personalData.getName() + " " + personalData.getLastName() + " decision is negative.";
            case CONTACT_REQUIRED:
                return "Sorry " + personalData.getName() + " " + personalData.getLastName() + ", bank requires additional documents. Our Consultant will contact you.";
            case POSITIVE:
                return "Congratulations, " + personalData.getName() + " " + personalData.getLastName() + ", decision is positive.";
            case NEGATIVE_CREDIT_RATING:
                return "Sorry, " + personalData.getName() + " " + personalData.getLastName() + ", decision is negative. Bank can borrow only " + new BigDecimal(creditRating).setScale(2);
            case NEGATIVE_REQUIREMENTS_NOT_MET:
                return "Sorry, " + personalData.getName() + " " + personalData.getLastName() + ", decision is negative. Minimum loan amount for mortgage is: "+MIN_LOAN_AMOUNT_MORTGAGE;
        }
        return null;
    }
}
