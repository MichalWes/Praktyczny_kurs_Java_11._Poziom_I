package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static pl.javaskills.creditapp.core.DecisionType.*;

class CreditApplicationServiceBDDTest {

    NaturalPersonScoringCalculator naturalPersonScoringCalculator = new NaturalPersonScoringCalculator(new IncomeCalculator(), new EducationCalculator(), new MaritalStatusCalculator());
    SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator(new IncomeCalculator(), new EducationCalculator(), new MaritalStatusCalculator());
    PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(naturalPersonScoringCalculator, selfEmployedScoringCalculator);
    private CreditApplicationService cut = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator());

    @Test
    @DisplayName("Should return NEGATIVE_REQUIREMENTS_NOT_MET when scoring is >= 400 and requested loan amount lower than 100000")
    public void test1() {
        //given
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withNumOfFamilyDependants(2)
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(MaritalStatus.MARRIED)
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

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
                .withPesel(null)
                .build();

        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder
                .create()
                .withType(Type.MORTGAGE)
                .withPeriod((byte) 30)
                .withAmount(50000)
                .build();

        LoanApplication loanApplication = CreditApplicationServiceTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(loanApplication);
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
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withNumOfFamilyDependants(2)
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(MaritalStatus.MARRIED)
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

        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
                .withNip(null)
                .withRegon(null)
                .withYearsSinceFounded(1)
                .build();

        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder
                .create()
                .withType(Type.MORTGAGE)
                .withPeriod((byte) 30)
                .withAmount(500000)
                .build();

        LoanApplication loanApplication = CreditApplicationServiceTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(loanApplication);
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
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withNumOfFamilyDependants(2)
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(MaritalStatus.MARRIED)
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

        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
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

        LoanApplication loanApplication = CreditApplicationServiceTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(loanApplication);
        DecisionType decisionType = decision.getDecisionType();
        //then
        assertEquals(CONTACT_REQUIRED,decisionType);
        assertEquals(400, decision.getScoring());
        assertEquals(252000.0,decision.getCreditRating());
    }
}
