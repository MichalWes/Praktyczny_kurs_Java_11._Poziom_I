package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import pl.javaskills.creditapp.core.bik.BikApi;
import pl.javaskills.creditapp.core.bik.ScoringRequest;
import pl.javaskills.creditapp.core.bik.ScoringResponse;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.scoring.*;
import pl.javaskills.creditapp.core.validation.*;
import pl.javaskills.creditapp.core.validation.reflection.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static pl.javaskills.creditapp.core.DecisionType.*;

class CreditApplicationServiceBDDTest {
    private List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
    private List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
    private BikApi bankApiMock = Mockito.mock(BikApi.class);
    private PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(new BikScoringCalculator(bankApiMock), new SelfEmployedScoringCalculator(), new EducationCalculator(), new IncomeCalculator(), new MaritalStatusCalculator(), new GuarantorsCalculator());
    private CreditApplicationService cut = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), new CreditApplicationValidator(new ObjectValidator(fieldProcessors, classProcessors)), new CompoundPostValidator(new ExpensesPostValidator(), new PurposeOfLoanPostValidator()));

    @BeforeEach
    public void init() {
        ScoringResponse res = new ScoringResponse();
        res.setScoring(0);
        BDDMockito.given(bankApiMock.getScoring(any(ScoringRequest.class))).willReturn(res);
    }

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
                .withCity("Gda??sk")
                .withState("Pomorskie")
                .build();

        Address correspondeceAddress = Address.Builder
                .create()
                .withStreet("Lipowa")
                .withHouseNumber("3")
                .withZipCode("80-500")
                .withCity("Gda??sk")
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
                .buildWithoutExpenses();

        List<FamilyMember> familyMembers = new ArrayList<>();

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withBirthDate(LocalDate.of(2000, 12, 1))
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Zdzis??aw")
                .withBirthDate(LocalDate.of(1995, 12, 1))
                .build());

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(contactData)
                .withFamilyMembers(familyMembers)
                .withPesel("82838283823")
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
        assertEquals(NEGATIVE_REQUIREMENTS_NOT_MET, decisionType);
        assertEquals(600, decision.getScoring());
        assertEquals(360000.0, decision.getCreditRating());
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
                .withCity("Gda??sk")
                .withState("Pomorskie")
                .build();

        Address correspondeceAddress = Address.Builder
                .create()
                .withStreet("Lipowa")
                .withHouseNumber("3")
                .withZipCode("80-500")
                .withCity("Gda??sk")
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
                .buildWithoutExpenses();

        List<FamilyMember> familyMembers = new ArrayList<>();

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withBirthDate(LocalDate.of(2000, 12, 1))
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Zdzis??aw")
                .withBirthDate(LocalDate.of(1986, 12, 1))
                .build());

        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(contactData)
                .withFamilyMembers(familyMembers)
                .withNip("12345678910")
                .withRegon(null)
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
        assertEquals(NEGATIVE_SCORING, decisionType);
        assertEquals(200, decision.getScoring());
        assertEquals(252000.0, decision.getCreditRating());
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
                .withCity("Gda??sk")
                .withState("Pomorskie")
                .build();

        Address correspondeceAddress = Address.Builder
                .create()
                .withStreet("Lipowa")
                .withHouseNumber("3")
                .withZipCode("80-500")
                .withCity("Gda??sk")
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
                .buildWithoutExpenses();

        List<FamilyMember> familyMembers = new ArrayList<>();

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withBirthDate(LocalDate.of(2000, 12, 1))
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Zdzis??aw")
                .withBirthDate(LocalDate.of(1993, 12, 1))
                .build());

        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(contactData)
                .withFamilyMembers(familyMembers)
                .withNip("12345678910")
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
        assertEquals(CONTACT_REQUIRED, decisionType);
        assertEquals(400, decision.getScoring());
        assertEquals(252000.0, decision.getCreditRating());
    }

    @Test
    @DisplayName("Should return NEGATIVE_REQUIREMENTS_NOT_MET because of too high personal expenses")
    public void test4() {
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
                .withCity("Gda??sk")
                .withState("Pomorskie")
                .build();

        Address correspondeceAddress = Address.Builder
                .create()
                .withStreet("Lipowa")
                .withHouseNumber("3")
                .withZipCode("80-500")
                .withCity("Gda??sk")
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

        Set<Expense> expenses = new HashSet<>();
        expenses.add(new Expense("personal", ExpenseType.PERSONAL, 1000.0));
        expenses.add(new Expense("personal1", ExpenseType.PERSONAL, 2000.0));

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1)
                .withExpenses(expenses)
                .buildWithExpenses();

        List<FamilyMember> familyMembers = new ArrayList<>();

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withBirthDate(LocalDate.of(2000, 12, 1))
                .build());

        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(contactData)
                .withFamilyMembers(familyMembers)
                .withNip("12345678910")
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
        assertEquals(NEGATIVE_REQUIREMENTS_NOT_MET, decisionType);
        assertEquals(800, decision.getScoring());
        assertEquals(504000.0, decision.getCreditRating());
    }


}
