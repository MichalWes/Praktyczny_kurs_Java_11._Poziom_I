package pl.javaskills.creditapp;

import  pl.javaskills.creditapp.client.ConsoleReader;
import pl.javaskills.creditapp.core.CreditApplicationService;
import pl.javaskills.creditapp.core.model.LoanApplication;

public class Main {

    public static void main(String[] args) {
        LoanApplication loanApplication = new ConsoleReader().readInputParameters();
        System.out.println("Hello, " + loanApplication.getPerson().getPersonalData().getName() + " " + loanApplication.getPerson().getPersonalData().getLastName() + "!");
        System.out.println(new CreditApplicationService().getDecision(loanApplication));
    }
}
