package pl.javaskills.creditapp;

import pl.javaskills.creditapp.client.DummyCreditApplicationReader;
import pl.javaskills.creditapp.core.CreditApplicationManager;
import pl.javaskills.creditapp.core.validation.CompoundPostValidator;
import pl.javaskills.creditapp.core.validation.ExpensesPostValidator;
import pl.javaskills.creditapp.core.validation.ObjectValidator;
import pl.javaskills.creditapp.core.validation.PurposeOfLoanPostValidator;
import pl.javaskills.creditapp.core.validation.reflection.*;
import pl.javaskills.creditapp.di.ClassInitializer;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static pl.javaskills.creditapp.core.Constants.DEFAULT_SYSTEM_LOCALE;
import static pl.javaskills.creditapp.core.Constants.DEFAULT_SYSTEM_ZONE_ID;

public class Main {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone(DEFAULT_SYSTEM_ZONE_ID));
        Locale.setDefault(DEFAULT_SYSTEM_LOCALE);
    }

    public static void main(String[] args) throws Exception {

        List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
        List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
        CompoundPostValidator compoundPostValidator = new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpensesPostValidator());
        ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);

        ClassInitializer classInitializer = new ClassInitializer();
        classInitializer.registerInstance(compoundPostValidator);
        classInitializer.registerInstance(objectValidator);
        CreditApplicationManager manager = (CreditApplicationManager) classInitializer.createInstance(CreditApplicationManager.class);

        manager.add(new DummyCreditApplicationReader().read());
//        manager.add(new DummyCreditApplicationReader().read());
//        manager.add(new DummyCreditApplicationReader().read());
//        manager.add(new DummyCreditApplicationReader().read());
//        manager.add(new DummyCreditApplicationReader().read());
//        manager.add(new DummyCreditApplicationReader().read());
//        manager.add(new DummyCreditApplicationReader().read());
//        manager.add(new DummyCreditApplicationReader().read());
        manager.startProcessing();
    }
}
