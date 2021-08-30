package pl.javaskills.creditapp;

import pl.javaskills.creditapp.client.ConsoleReader;
import pl.javaskills.creditapp.core.*;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;

public class Main {

    public static void main(String[] args) {
        LoanApplication loanApplication = new ConsoleReader().readInputParameters();
        PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(new SelfEmployedScoringCalculator(), new EducationCalculator(), new IncomeCalculator(), new MaritalStatusCalculator());
        CreditApplicationService creditApplicationService = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator());
        CreditApplicationDecision decision = creditApplicationService.getDecision(loanApplication);
        System.out.println(decision.getDecisionString());
    }
}
