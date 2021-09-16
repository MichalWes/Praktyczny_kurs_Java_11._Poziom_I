package pl.javaskills.creditapp;

import pl.javaskills.creditapp.client.DummyCreditApplicationReader;
import pl.javaskills.creditapp.core.*;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.GuarantorsCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;
import pl.javaskills.creditapp.core.validation.*;
import pl.javaskills.creditapp.core.validation.reflection.*;

import java.util.Set;

public class Main {

    public static void main(String[] args) {

        PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(new SelfEmployedScoringCalculator(), new EducationCalculator(), new IncomeCalculator(), new MaritalStatusCalculator(), new GuarantorsCalculator());
        Set<FieldAnnotationProcessor> fieldProcessors = Set.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
        Set<ClassAnnotationProcessor> classProcessors = Set.of(new ExactlyOneNotNullAnnotationProcessor());
        CreditApplicationService creditApplicationService = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), new CreditApplicationValidator(new ObjectValidator(fieldProcessors, classProcessors)), new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpensesPostValidator()));
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
