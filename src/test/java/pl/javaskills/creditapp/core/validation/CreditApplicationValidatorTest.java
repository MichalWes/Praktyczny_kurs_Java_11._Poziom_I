package pl.javaskills.creditapp.core.validation;

import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.PersonScoringCalculatorFactory;
import pl.javaskills.creditapp.core.SelfEmployedScoringCalculator;
import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.GuarantorsCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;
import pl.javaskills.creditapp.core.validation.reflection.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class CreditApplicationValidatorTest {
    private Set<FieldAnnotationProcessor> fieldProcessors = Set.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
    private Set<ClassAnnotationProcessor> classProcessors = Set.of(new ExactlyOneNotNullAnnotationProcessor());
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
                .buildWithoutExpenses();

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
                .withPesel("12345678910")
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

        CreditApplication creditApplication= new CreditApplication(person, purposeOfLoan, guarantors);

        //when
        cut.validate(creditApplication);



    }

}