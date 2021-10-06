package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.exception.RequirementNotMetCause;
import pl.javaskills.creditapp.core.model.PersonalData;
import pl.javaskills.creditapp.di.Inject;

import java.util.Optional;

public class CreditApplicationDecision {
    private final DecisionType decisionType;
    private final Optional<RequirementNotMetCause> requirementNotMetCause;
    @Inject
    private PersonalData personalData;
    private final Double creditRating;
    private final int scoring;

    public CreditApplicationDecision(DecisionType decisionType, PersonalData personalData, Double creditRating, int scoring) {
        this.decisionType = decisionType;
        this.personalData = personalData;
        this.creditRating = creditRating;
        this.scoring = scoring;
        this.requirementNotMetCause = Optional.empty();

    }

    public CreditApplicationDecision(DecisionType decisionType, PersonalData personalData, Double creditRating, int scoring, RequirementNotMetCause cause) {
        this.decisionType = decisionType;
        this.personalData = personalData;
        this.creditRating = creditRating;
        this.scoring = scoring;
        this.requirementNotMetCause = Optional.of(cause);
    }

    public Optional<RequirementNotMetCause> getRequirementNotMetCause() {
        return requirementNotMetCause;
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


}
