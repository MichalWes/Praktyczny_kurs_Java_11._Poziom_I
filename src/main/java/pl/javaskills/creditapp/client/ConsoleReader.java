package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.model.*;

import java.util.Scanner;

public class ConsoleReader {

    public LoanApplication readInputParameters(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = in.next();

        System.out.println("Enter your last name: ");
        String lastName = in.next();

        System.out.println("Enter your mother’s maiden name: ");
        String mothersMaidenName = in.next();

        System.out.println("What is your marital status? (SINGLE, MARRIED, DIVORCED, WIDOWED, SEPARATED): ");
        MaritalStatus maritalStatus = MaritalStatus.valueOf(in.next().toUpperCase());

        System.out.println("What is your education level? (NONE, PRIMARY, MIDDLE, SECONDARY, POST_SECONDARY, TERTIARY): ");
        String education = in.next().toUpperCase();

        System.out.println("Enter your phone number: ");
        String phoneNumber = in.next();

        System.out.println("Enter your email: ");
        String email = in.next();

        System.out.println("How many sources of income do you have?: ");
        int sourcesOfIncome = in.nextInt();
        FinanceData financeData = new FinanceData();
        SourceOfIncome[] tempSourceOfIncome = new SourceOfIncome[sourcesOfIncome];

        for (int i = 0; i < sourcesOfIncome; i++){
            System.out.println("Enter type of source of income "+(i+1)+" (EMPLOYMENT_CONTRACT | SELF_EMPLOYMENT | RETIREMENT");
            SourceOfIncome sourceOfIncome = new SourceOfIncome();
            sourceOfIncome.setIncomeType(IncomeType.valueOf(in.next().toUpperCase()));
            System.out.println("Enter net monthly income of source of income "+(i+1));
            sourceOfIncome.setNetMontlyIncome(in.nextDouble());
            tempSourceOfIncome[i] = sourceOfIncome;
        }
        financeData.addIncomeType(tempSourceOfIncome);

        System.out.println("Enter number of family dependants (including applicant):");
        int numOfFamilyDependants = in.nextInt();

        System.out.println("What is purpose of loan? (MORTGAGE | PERSONAL_LOAN): ");
        String type = in.next().toUpperCase();

        System.out.println("Enter loan amount: ");
        double amount = in.nextDouble();

        System.out.println("Enter loan period (in years): ");
        byte period = in.nextByte();

        PersonalData personalData = new PersonalData(name, lastName, mothersMaidenName);
        personalData.setMaritalStatus(maritalStatus);
        personalData.setEducation(Education.valueOf(education));
        personalData.setNumOfFamilyDependants(numOfFamilyDependants);

        ContactData contactData = new ContactData();
        contactData.setPhoneNumber(phoneNumber);
        contactData.setEmail(email);

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan();
        purposeOfLoan.setType(Type.valueOf(type));
        purposeOfLoan.setAmount(amount);
        purposeOfLoan.setPeriod(period);

        return new LoanApplication(new Person(personalData, financeData, contactData), purposeOfLoan);
    }
}
