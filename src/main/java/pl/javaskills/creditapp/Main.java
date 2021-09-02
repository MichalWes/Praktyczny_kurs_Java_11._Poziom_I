package pl.javaskills.creditapp;

import pl.javaskills.creditapp.client.CreditApplicationReader;
import pl.javaskills.creditapp.client.DummyCreditApplicationReader;
import pl.javaskills.creditapp.core.*;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;
import pl.javaskills.creditapp.core.validation.*;

public class Main {

    public static void main(String[] args) {

        PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(new SelfEmployedScoringCalculator(), new EducationCalculator(), new IncomeCalculator(), new MaritalStatusCalculator());
        CreditApplicationService creditApplicationService = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), new CreditApplicationValidator(new PersonValidator(new PersonalDataValidator(), new ContactDataValidator(), new FinanceDataValidator()), new PurposeOfLoanValidator()));
        CreditApplicationManager manager = new CreditApplicationManager(creditApplicationService);
        manager.add(new DummyCreditApplicationReader().read());
        manager.add(new DummyCreditApplicationReader().read());
        manager.add(new DummyCreditApplicationReader().read());
        manager.add(new DummyCreditApplicationReader().read());
        manager.add(new DummyCreditApplicationReader().read());
        manager.add(new DummyCreditApplicationReader().read());
        manager.add(new DummyCreditApplicationReader().read());
        manager.add(new DummyCreditApplicationReader().read());
        manager.startProcessing();
    }
}
