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

        String maritalStatusStr;
        do{
            System.out.print("What is your marital status? (");
            EnumValidator.printMaritalStatsEnum();
            maritalStatusStr = in.next().toUpperCase();
        }while(!EnumValidator.validateMaritalStatus(maritalStatusStr));
        MaritalStatus maritalStatus = MaritalStatus.valueOf(maritalStatusStr);

        String educationStr;
        do{
            System.out.print("What is your education level? (");
            EnumValidator.printEducationEnum();
            educationStr = in.next().toUpperCase();
        }while(!EnumValidator.validateEducation(educationStr));
        Education education = Education.valueOf(educationStr);


        System.out.println("Enter your phone number: ");
        String phoneNumber = in.next();

        System.out.println("Enter your email: ");
        String email = in.next();

        System.out.println("How many sources of income do you have?: ");
        int sourcesOfIncome = in.nextInt();
        FinanceData financeData = new FinanceData();
        SourceOfIncome[] tempSourceOfIncome = new SourceOfIncome[sourcesOfIncome];

        for (int i = 0; i < sourcesOfIncome; i++){
            String incomeTypeStr;
            do{
                System.out.print("Enter type of source of income "+(i+1)+" (");
                EnumValidator.printIncomeTypeEnum();
                incomeTypeStr = in.next().toUpperCase();
            }while(!EnumValidator.validateIncomeType(incomeTypeStr));
            IncomeType incomeType = IncomeType.valueOf(incomeTypeStr);
            SourceOfIncome sourceOfIncome = new SourceOfIncome();
            sourceOfIncome.setIncomeType(incomeType);
            System.out.println("Enter net monthly income of source of income "+(i+1));
            sourceOfIncome.setNetMontlyIncome(in.nextDouble());
            tempSourceOfIncome[i] = sourceOfIncome;
        }
        financeData.addIncomeType(tempSourceOfIncome);

        System.out.println("Enter number of family dependants (including applicant):");
        int numOfFamilyDependants = in.nextInt();

        String typeStr;
        do{
            System.out.print("What is purpose of loan? (");
            EnumValidator.printTypeEnum();
            typeStr = in.next().toUpperCase();
        }while(!EnumValidator.validateType(typeStr));
        Type type = Type.valueOf(typeStr);

        System.out.println("Enter loan amount: ");
        double amount = in.nextDouble();

        System.out.println("Enter loan period (in years): ");
        byte period = in.nextByte();

        PersonalData personalData = new PersonalData(name, lastName, mothersMaidenName);
        personalData.setMaritalStatus(maritalStatus);
        personalData.setEducation(education);
        personalData.setNumOfFamilyDependants(numOfFamilyDependants);

        ContactData contactData = new ContactData();
        contactData.setPhoneNumber(phoneNumber);
        contactData.setEmail(email);

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan();
        purposeOfLoan.setType(type);
        purposeOfLoan.setAmount(amount);
        purposeOfLoan.setPeriod(period);

        return new LoanApplication(new Person(personalData, financeData, contactData), purposeOfLoan);
    }
}
