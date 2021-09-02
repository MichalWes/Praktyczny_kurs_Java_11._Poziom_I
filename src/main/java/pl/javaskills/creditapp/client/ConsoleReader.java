package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.Constants;
import pl.javaskills.creditapp.core.model.*;

import java.util.Scanner;

public class ConsoleReader implements CreditApplicationReader {

    @Override
    public LoanApplication read(){
        Scanner in = new Scanner(System.in);
        String name = getName(in);
        String lastName = getLastName(in);
        String mothersMaidenName = getMothersMaidenName(in);
        MaritalStatus maritalStatus = getMaritalStatus(in);
        Education education = getEducation(in);
        String phoneNumber = getPhoneNumber(in);
        String email = getEmail(in);
        FinanceData financeData = getFinanceData(in);
        int numOfFamilyDependants = getNumOfFamilyDependants(in);
        Type type = getType(in);
        double amount = getAmount(in);
        byte period = getPeriod(in);

        System.out.println("Enter your home address street: ");
        String hStreet = in.next();
        System.out.println("Enter your home address city: ");
        String hCity = in.next();
        System.out.println("Enter your home address house number: ");
        String hHouseNumber = in.next();
        System.out.println("Enter your home address zipcode: ");
        String hZipCode = in.next();
        System.out.println("Enter your home address state: ");
        String hState = in.next();
        System.out.println("Enter your correspondence address street: ");
        String cStreet = in.next();
        System.out.println("Enter your correspondence address city: ");
        String cCity = in.next();
        System.out.println("Enter your correspondence address house number: ");
        String cHouseNumber = in.next();
        System.out.println("Enter your correspondence address street: ");
        String cZipCode = in.next();
        System.out.println("Enter your correspondence address state: ");
        String cState = in.next();

        PersonalData personalData = PersonalData.Builder
                .create()
                .withName(name)
                .withLastName(lastName)
                .withMothersMaidenName(mothersMaidenName)
                .withMaritalStatus(maritalStatus)
                .withEducation(education)
                .build();

        Address homeAddress = Address.Builder
                .create()
                .withStreet(hStreet)
                .withHouseNumber(hHouseNumber)
                .withZipCode(hZipCode)
                .withCity(hCity)
                .withState(hState)
                .build();

        Address correspondeceAddress = Address.Builder
                .create()
                .withStreet(cStreet)
                .withHouseNumber(cHouseNumber)
                .withZipCode(cZipCode)
                .withCity(cCity)
                .withState(cState)
                .build();

        ContactData contactData = ContactData.Builder
                .create()
                .withEmail(email)
                .withPhoneNumber(phoneNumber)
                .withHomeAddress(homeAddress)
                .withCorrespondeceAddress(correspondeceAddress)
                .build();

        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder
                .create()
                .withType(type)
                .withAmount(amount)
                .withPeriod(period)
                .build();

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(contactData)
                .build();

        return new LoanApplication(person, purposeOfLoan);
    }

    private String getName(Scanner in) {
        String nameStr;
        do{
            System.out.println("Enter your name: ");
            nameStr = in.next();

        }while(!StringValidator.validate(nameStr, Constants.NAME_REGEX));
        String name = nameStr;
        return name;
    }

    private String getLastName(Scanner in) {
        String lastNameStr;
        do{
            System.out.println("Enter your last name: ");
            lastNameStr = in.next();

        }while(!StringValidator.validate(lastNameStr, Constants.LAST_NAME_REGEX, Constants.LAST_NAME_REGEX2));
        String lastName = lastNameStr;
        return lastName;
    }

    private String getMothersMaidenName(Scanner in) {
        String mothersMaidenNameStr;
        do{
            System.out.println("Enter your mother’s maiden name: ");
            mothersMaidenNameStr = in.next();

        }while(!StringValidator.validate(mothersMaidenNameStr, Constants.LAST_NAME_REGEX, Constants.LAST_NAME_REGEX2));
        return mothersMaidenNameStr;
    }

    private String getPhoneNumber(Scanner in) {
        String phoneNumberStr;
        do{
            System.out.println("Enter your phone number: ");
            phoneNumberStr = in.next();

        }while(!PhoneValidator.validate(phoneNumberStr));
        String phoneNumber = phoneNumberStr;
        return phoneNumber;
    }

    private String getEmail(Scanner in) {
        String emailStr;
        do{
            System.out.println("Enter your email: ");
            emailStr = in.next();

        }while(!StringValidator.validate(emailStr, Constants.EMAIL_REGEX));
        return emailStr;
    }

    private int getNumOfFamilyDependants(Scanner in) {
        String numOfFamilyDependantsStr;
        do{
            System.out.println("Enter number of family dependants (including applicant):");
            numOfFamilyDependantsStr = in.next();

        }while(!NumberValidator.validateInteger(numOfFamilyDependantsStr, 0, Integer.MAX_VALUE));
        int numOfFamilyDependants = Integer.parseInt(numOfFamilyDependantsStr);
        return numOfFamilyDependants;
    }

    private double getAmount(Scanner in) {
        String amountStr;
        do{
            System.out.println("Enter loan amount: ");
            amountStr = in.next();

        }while(!NumberValidator.validateDouble(amountStr, 0, Double.MAX_VALUE));
        double amount = Double.parseDouble(amountStr);
        return amount;
    }

    private byte getPeriod(Scanner in) {
        String periodStr;
        do{
            System.out.println("Enter loan period (in years): ");
            periodStr = in.next();

        }while(!NumberValidator.validateInteger(periodStr, 5, 10, 15, 20, 25, 30));
        byte period = (byte)Integer.parseInt(periodStr);
        return period;
    }


    private FinanceData getFinanceData(Scanner in) {
        System.out.println("How many sources of income do you have?: ");
        int sourcesOfIncome = in.nextInt();

        SourceOfIncome[] tempSourceOfIncome = new SourceOfIncome[sourcesOfIncome];

        for (int i = 0; i < sourcesOfIncome; i++){
            String incomeTypeStr;
            do{
                System.out.print("Enter type of source of income "+(i+1));
                printIncomeTypeEnum();
                incomeTypeStr = in.next().toUpperCase();
            }while(!EnumValidator.validateIncomeType(incomeTypeStr));
            IncomeType incomeType = IncomeType.valueOf(incomeTypeStr);
            System.out.println("Enter net monthly income of source of income "+(i+1));
            SourceOfIncome sourceOfIncome = SourceOfIncome.Builder
                    .create()
                    .withIncomeType(incomeType)
                    .withNetMontlyIncome(in.nextDouble())
                    .build();
            tempSourceOfIncome[i] = sourceOfIncome;
        }

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(tempSourceOfIncome)
                .build();
        return financeData;
    }

    private Education getEducation(Scanner in) {
        String educationStr;
        do{
            System.out.print("What is your education level?");
            printEducationEnum();
            educationStr = in.next().toUpperCase();
        }while(!EnumValidator.validateEducation(educationStr));
        Education education = Education.valueOf(educationStr);
        return education;
    }

    private MaritalStatus getMaritalStatus(Scanner in) {
        String maritalStatusStr;
        do{
            System.out.print("What is your marital status?");
            printMaritalStatsEnum();
            maritalStatusStr = in.next().toUpperCase();
        }while(!EnumValidator.validateMaritalStatus(maritalStatusStr));
        MaritalStatus maritalStatus = MaritalStatus.valueOf(maritalStatusStr);
        return maritalStatus;
    }

    private Type getType(Scanner in) {
        String typeStr;
        do{
            System.out.print("What is purpose of loan?");
            printTypeEnum();
            typeStr = in.next().toUpperCase();
        }while(!EnumValidator.validateType(typeStr));
        Type type = Type.valueOf(typeStr);
        return type;
    }

    private void printIncomeTypeEnum() {
        System.out.print(" (");
        for(int j=0; j<IncomeType.values().length; j++){
            System.out.print(IncomeType.values()[j]);
            if (j<(IncomeType.values().length-1))
                System.out.print(" | ");
        }
        System.out.println("): ");
    }
    private void printEducationEnum() {
        System.out.print(" (");
        for(int j=0; j<Education.values().length; j++){
            System.out.print(Education.values()[j]);
            if (j<(Education.values().length-1))
                System.out.print(" | ");
        }
        System.out.println("): ");
    }
    private void printMaritalStatsEnum() {
        System.out.print(" (");
        for(int j=0; j<MaritalStatus.values().length; j++){
            System.out.print(MaritalStatus.values()[j]);
            if (j<(MaritalStatus.values().length-1))
                System.out.print(" | ");
        }
        System.out.println("): ");
    }
    private void printTypeEnum() {
        System.out.print(" (");
        for(int j=0; j<Type.values().length; j++){
            System.out.print(Type.values()[j]);
            if (j<(Type.values().length-1))
                System.out.print(" | ");
        }
        System.out.println("): ");
    }

}
