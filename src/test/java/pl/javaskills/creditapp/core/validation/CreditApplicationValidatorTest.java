package pl.javaskills.creditapp.core.validation;

import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.validation.reflection.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static pl.javaskills.creditapp.core.Constants.CLIENT_TIME_ZONE_ID;
import static pl.javaskills.creditapp.core.Constants.DEFAULT_SYSTEM_LOCALE;

class CreditApplicationValidatorTest {
    private List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
    private List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
    private ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);
    private CreditApplicationValidator cut = new CreditApplicationValidator(objectValidator);

    @Test
    public void test() throws ValidationException {
        //given
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
                .withBirthDate(LocalDate.of(2000, 12, 1))
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Zdzisław")
                .withBirthDate(LocalDate.of(1993, 12, 1))
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Alicja")
                .withBirthDate(LocalDate.of(1986, 12, 1))
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Monstrancja")
                .withBirthDate(LocalDate.of(1970, 12, 1))
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
                .withBirthDate(LocalDate.of(1995, 12, 1))
                .build();

        Guarantor guarantor2 = Guarantor.Builder
                .create()
                .withPesel("95222535332")
                .withBirthDate(LocalDate.of(1975, 12, 1))
                .build();

        Set<Guarantor> guarantors = new TreeSet<>();
        guarantors.add(guarantor1);
        guarantors.add(guarantor2);

        CreditApplication creditApplication = new CreditApplication(CLIENT_TIME_ZONE_ID, DEFAULT_SYSTEM_LOCALE, person, purposeOfLoan, guarantors);

        //when
        cut.validate(creditApplication);


    }

}