package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.Person;

import java.util.Scanner;

public class ConsoleReader {

    public Person readInputParameters(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = in.next();
        System.out.println();

        System.out.println("Enter your last name: ");
        String lastName = in.next();
        System.out.println();

        System.out.println("Enter your mother’s maiden name: ");
        String mothersMaidenName = in.next();
        System.out.println();

        System.out.println("Enter total monthly income in PLN: ");
        double totalMonthlyIncomePLN = in.nextDouble();
        System.out.println();

        System.out.println("Are you married: ");
        boolean married = in.nextBoolean();
        System.out.println();

        System.out.println("Enter number of family dependants (including applicant):");
        int numOfFamilyDependants = in.nextInt();
        System.out.println();

        Person person = new Person(name, lastName, mothersMaidenName);
        person.setTotalMonthlyIncomePLN(totalMonthlyIncomePLN);
        person.setMarried(married);
        person.setNumOfFamilyDependants(numOfFamilyDependants);

        return person;
    }
}
