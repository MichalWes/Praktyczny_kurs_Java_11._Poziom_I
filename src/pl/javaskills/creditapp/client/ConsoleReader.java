package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.model.*;

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

        System.out.println("What is your marital status? (SINGLE, MARRIED, DIVORCED, WIDOWED, SEPARATED): ");
        String maritalStatus = in.next().toUpperCase();
        System.out.println();

        System.out.println("What is your education level? (NONE, PRIMARY, MIDDLE, SECONDARY, POST_SECONDARY, TERTIARY): ");
        String education = in.next().toUpperCase();
        System.out.println();

        System.out.println("Enter your phone number: ");
        String phoneNumber = in.next();
        System.out.println();

        System.out.println("Enter your email: ");
        String email = in.next();
        System.out.println();


        System.out.println("Enter total monthly income in PLN: ");
        double totalMonthlyIncomePLN = in.nextDouble();
        System.out.println();

        System.out.println("Enter number of family dependants (including applicant):");
        int numOfFamilyDependants = in.nextInt();
        System.out.println();

        PersonalData personalData = new PersonalData(name, lastName, mothersMaidenName);
        personalData.setMaritalStatus(MaritalStatus.valueOf(maritalStatus));
        personalData.setEducation(Education.valueOf(education));
        personalData.setTotalMonthlyIncomePLN(totalMonthlyIncomePLN);
        personalData.setNumOfFamilyDependants(numOfFamilyDependants);

        ContactData contactData = new ContactData();
        contactData.setPhoneNumber(phoneNumber);
        contactData.setEmail(email);

        return new Person(personalData, contactData);
    }
}
