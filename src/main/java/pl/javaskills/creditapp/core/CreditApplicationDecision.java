package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.LoanApplication;

public class CreditApplicationDecision {
    public String getDecision(LoanApplication loanApplication){
        DecisionType decisionType = new CreditApplicationService().getDecision(loanApplication);

        String decision ="";
        switch (decisionType) {
            case NEGATIVE_SCORING:
                decision = "Sorry " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + " decision is negative.";
                break;
            case CONTACT_REQUIRED:
                decision = "Sorry " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", bank requires additional documents. Our Consultant will contact you.";
                break;
            case POSITIVE:
                decision = "Congratulations, " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", decision is positive.";
                break;
            case NEGATIVE_CREDIT_RATING:
                decision = "Sorry, " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + ", decision is negative. Bank can borrow only " + decisionType.getCreditRating();
        }
        return decision;
    }

}
