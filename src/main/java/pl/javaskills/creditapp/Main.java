package pl.javaskills.creditapp;

import pl.javaskills.creditapp.client.ConsoleReader;
import pl.javaskills.creditapp.core.*;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.PersonScoringCalculator;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;

public class Main {

    public static void main(String[] args) {
        LoanApplication loanApplication = new ConsoleReader().readInputParameters();
        CreditApplicationDecision decision = new CreditApplicationService(new PersonScoringCalculator(new IncomeCalculator(), new EducationCalculator(), new MaritalStatusCalculator()), new CreditRatingCalculator()).getDecision(loanApplication);
        System.out.println(decision.getDecisionString());
    }
}
