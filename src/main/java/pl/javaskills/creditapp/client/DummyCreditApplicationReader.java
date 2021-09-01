package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.model.*;

public class DummyCreditApplicationReader implements CreditApplicationReader {

    @Override
    public LoanApplication read() {

        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("Michal")
                .withLastName("Wilenski")
                .withMothersMaidenName("Wiedenska")
                .withMaritalStatus(MaritalStatus.MARRIED)
                .withEducation(Education.MIDDLE)
                .withNumOfFamilyDependants(2)
                .build();

        Address homeAddress = Address.Builder
                .create()
                .withStreet("Lipowa")
                .withHouseNumber("3")
                .withZipCode("80-500")
                .withCity("Gdańsk")
                .withState("Pomorskie")
                .build();

        Address correspondeceAddress = Address.Builder
                .create()
                .withStreet("Lipowa")
                .withHouseNumber("3")
                .withZipCode("80-500")
                .withCity("Gdańsk")
                .withState("Pomorskie")
                .build();

        ContactData contactData = ContactData.Builder
                .create()
                .withEmail("halko@wp.pl")
                .withPhoneNumber("+48959599599")
                .withHomeAddress(homeAddress)
                .withCorrespondeceAddress(correspondeceAddress)
                .build();

        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder
                .create()
                .withType(Type.MORTGAGE)
                .withAmount(300000.0)
                .withPeriod((byte)30)
                .build();

        SourceOfIncome sourceOfIncome = SourceOfIncome.Builder
                .create()
                .withIncomeType(IncomeType.SELF_EMPLOYMENT)
                .withNetMontlyIncome(10000)
                .build();

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(sourceOfIncome)
                .build();

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(contactData)
                .build();

        return new LoanApplication(person, purposeOfLoan);
    }
}
