package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.LoanApplication;

public class CreditApplicationDecision {
    public String getDecisionString(LoanApplication loanApplication){
        DecisionType decisionType = new CreditApplicationService(new PersonScoringCalculator()).getDecision(loanApplication);

        switch (decisionType) {
            case NEGATIVE_SCORING:
                return "Sorry " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + " decision is negative.";
            case CONTACT_REQUIRED:
                return "Sorry " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", bank requires additional documents. Our Consultant will contact you.";
            case POSITIVE:
                return "Congratulations, " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", decision is positive.";
            case NEGATIVE_CREDIT_RATING:
                return "Sorry, " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", decision is negative. Bank can borrow only " + decisionType.getCreditRating();
        }
        return null;
    }


}
