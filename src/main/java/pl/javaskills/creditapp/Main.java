package pl.javaskills.creditapp;

import pl.javaskills.creditapp.client.ConsoleReader;
import pl.javaskills.creditapp.core.*;
import pl.javaskills.creditapp.core.model.LoanApplication;
import pl.javaskills.creditapp.core.model.Person;

public class Main {

    public static void main(String[] args) {
        LoanApplication loanApplication = new ConsoleReader().readInputParameters();
        CreditApplicationDecision decision = new CreditApplicationService(new PersonScoringCalculator(), new CreditRatingCalculator()).getDecision(loanApplication);
        System.out.println(decision.getDecisionString());
    }
}
