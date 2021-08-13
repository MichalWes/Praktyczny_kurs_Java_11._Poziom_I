package pl.javaskills.creditapp;

import  pl.javaskills.creditapp.client.ConsoleReader;
import pl.javaskills.creditapp.core.CreditApplicationService;
import pl.javaskills.creditapp.core.model.Person;

public class Main {

    public static void main(String[] args) {
        Person person = new ConsoleReader().readInputParameters();
        System.out.println("Hello, " + person.getPersonalData().getName() + " " + person.getPersonalData().getLastName() + "!");
        System.out.println(new CreditApplicationService().getDecision(person));
    }
}
