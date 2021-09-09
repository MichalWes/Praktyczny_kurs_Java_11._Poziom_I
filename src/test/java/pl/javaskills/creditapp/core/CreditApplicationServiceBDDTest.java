package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.GuarantorsCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;
import pl.javaskills.creditapp.core.validation.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static pl.javaskills.creditapp.core.DecisionType.*;

class CreditApplicationServiceBDDTest {

    PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(new SelfEmployedScoringCalculator(), new EducationCalculator(), new IncomeCalculator(), new MaritalStatusCalculator(), new GuarantorsCalculator());
    private CreditApplicationService cut = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), new CreditApplicationValidator(new PersonValidator(new PersonalDataValidator(),new ContactDataValidator(),new FinanceDataValidator()),new PurposeOfLoanValidator(), new GuarantorValidator()));

    @Test
    @DisplayName("Should return NEGATIVE_REQUIREMENTS_NOT_MET when scoring is >= 400 and requested loan amount lower than 100000")
    public void test1() {
        //given
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("Testtest")
                .withLastName("Testtest")
                .withMothersMaidenName("Testtest")
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(MaritalStatus.MARRIED)
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

        SourceOfIncome source1 = SourceOfIncome.Builder
                .create()
                .withIncomeType(IncomeType.SELF_EMPLOYMENT)
                .withNetMontlyIncome(10000)
                .build();

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1)
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

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(contactData)
                .withFamilyMembers(familyMembers)
                .withPesel("828382838238")
                .build();

        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder
                .create()
                .withType(Type.MORTGAGE)
                .withPeriod((byte) 30)
                .withAmount(50000)
                .build();

        CreditApplication creditApplication = CreditApplicationServiceTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        DecisionType decisionType = decision.getDecisionType();
        //then
        assertEquals(NEGATIVE_REQUIREMENTS_NOT_MET,decisionType);
        assertEquals(600, decision.getScoring());
        assertEquals(360000.0,decision.getCreditRating());
    }

    @Test
    @DisplayName("Should return NEGATIVE_REQUIREMENTS_NOT_MET when scoring is >= 400 and requested loan amount lower than 100000")
    public void test2() {
        //given
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("Testtest")
                .withLastName("Testtest")
                .withMothersMaidenName("Testtest")
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(MaritalStatus.MARRIED)
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

        SourceOfIncome source1 = SourceOfIncome.Builder
                .create()
                .withIncomeType(IncomeType.SELF_EMPLOYMENT)
                .withNetMontlyIncome(7000)
                .build();

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1)
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

        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(contactData)
                .withFamilyMembers(familyMembers)
                .withNip("43543545")
                .withRegon("43434343")
                .withYearsSinceFounded(1)
                .build();

        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder
                .create()
                .withType(Type.MORTGAGE)
                .withPeriod((byte) 30)
                .withAmount(500000)
                .build();

        CreditApplication creditApplication = CreditApplicationServiceTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        DecisionType decisionType = decision.getDecisionType();
        //then
        assertEquals(NEGATIVE_SCORING,decisionType);
        assertEquals(200, decision.getScoring());
        assertEquals(252000.0,decision.getCreditRating());
    }

    @Test
    @DisplayName("Should return NEGATIVE_REQUIREMENTS_NOT_MET when scoring is >= 400 and requested loan amount lower than 100000")
    public void test3() {
        //given
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("Testtest")
                .withLastName("Testtest")
                .withMothersMaidenName("Testtest")
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(MaritalStatus.MARRIED)
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

        SourceOfIncome source1 = SourceOfIncome.Builder
                .create()
                .withIncomeType(IncomeType.SELF_EMPLOYMENT)
                .withNetMontlyIncome(7000)
                .build();

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1)
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

        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(contactData)
                .withFamilyMembers(familyMembers)
                .withNip(null)
                .withRegon(null)
                .withYearsSinceFounded(3)
                .build();

        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder
                .create()
                .withType(Type.MORTGAGE)
                .withPeriod((byte) 30)
                .withAmount(500000)
                .build();

        CreditApplication creditApplication = CreditApplicationServiceTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        DecisionType decisionType = decision.getDecisionType();
        //then
        assertEquals(CONTACT_REQUIRED,decisionType);
        assertEquals(400, decision.getScoring());
        assertEquals(252000.0,decision.getCreditRating());
    }
}
