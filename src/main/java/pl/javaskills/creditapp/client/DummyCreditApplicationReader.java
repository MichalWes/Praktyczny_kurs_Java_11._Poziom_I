package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.util.AgeUtils;

import java.time.LocalDate;
import java.util.*;

import static pl.javaskills.creditapp.core.Constants.CLIENT_TIME_ZONE_ID;
import static pl.javaskills.creditapp.core.Constants.DEFAULT_SYSTEM_LOCALE;

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
                .withPeriod((byte) 30)
                .build();

        SourceOfIncome sourceOfIncome = SourceOfIncome.Builder
                .create()
                .withIncomeType(IncomeType.SELF_EMPLOYMENT)
                .withNetMontlyIncome(20000)
                .build();

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(sourceOfIncome)
                .buildWithoutExpenses();

        List<FamilyMember> familyMembers = new ArrayList<>();

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withBirthDate(LocalDate.of(1988, 12, 1))
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Zdzisław")
                .withBirthDate(LocalDate.of(1989, 03, 26))
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Alicja")
                .withBirthDate(LocalDate.of(1958, 12, 1))
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Monstrancja")
                .withBirthDate(LocalDate.of(1954, 12, 1))
                .build());

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(contactData)
                .withFamilyMembers(familyMembers)
                .withPesel("12345678910")
                .build();

        Guarantor guarantor1 = Guarantor.Builder
                .create()
                .withPesel("95222535353")
                .withBirthDate(LocalDate.of(1989, 03, 1))
                .build();

        Guarantor guarantor2 = Guarantor.Builder
                .create()
                .withPesel("95222535332")
                .withBirthDate(AgeUtils.generateBirthDate(32))
                .build();

        Set<Guarantor> guarantors = new TreeSet<>();
        guarantors.add(guarantor1);
        guarantors.add(guarantor2);

        return new CreditApplication(new Locale("pl", "PL"), CLIENT_TIME_ZONE_ID, person, purposeOfLoan, guarantors);
    }
}
