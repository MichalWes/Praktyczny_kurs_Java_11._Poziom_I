package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.model.*;

import java.util.*;

public class DummyCreditApplicationReader implements CreditApplicationReader {

    @Override
    public CreditApplication read() {

        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("Michal")
                .withLastName("Wilenski")
                .withMothersMaidenName("Wiedenska")
                .withMaritalStatus(MaritalStatus.MARRIED)
                .withEducation(Education.MIDDLE)
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
                .withNetMontlyIncome(20000)
                .build();

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(sourceOfIncome)
                .build();

        List<FamilyMember> familyMembers = new ArrayList<>();

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withAge(20)
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Zdzisław")
                .withAge(27)
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Alicja")
                .withAge(34)
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Monstrancja")
                .withAge(56)
                .build());

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(contactData)
                .withFamilyMembers(familyMembers)
                .build();

        Guarantor guarantor1 = Guarantor.Builder
                .create()
                .withPesel("95222535353")
                .withAge(25)
                .build();

        Guarantor guarantor2 = Guarantor.Builder
                .create()
                .withPesel("95222535332")
                .withAge(45)
                .build();

        Set<Guarantor> guarantors = new TreeSet<>();
        guarantors.add(guarantor1);
        guarantors.add(guarantor2);

        System.out.println(person.getFamilyMembers());
        System.out.println(person.getFamilyMembersSortedByName());

        return new CreditApplication(person, purposeOfLoan, guarantors);
    }
}
