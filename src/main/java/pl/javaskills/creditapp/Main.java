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
        NaturalPersonScoringCalculator naturalPersonScoringCalculator = new NaturalPersonScoringCalculator(new IncomeCalculator(), new EducationCalculator(), new MaritalStatusCalculator());
        SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator(new IncomeCalculator(), new EducationCalculator(), new MaritalStatusCalculator());
        CreditApplicationService creditApplicationService = new CreditApplicationService(new PersonScoringCalculatorFactory(naturalPersonScoringCalculator, selfEmployedScoringCalculator), new CreditRatingCalculator());
        CreditApplicationDecision decision = creditApplicationService.getDecision(loanApplication);
        System.out.println(decision.getDecisionString());
    }
}
