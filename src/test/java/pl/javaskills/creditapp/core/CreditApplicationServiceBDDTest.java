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

    private CreditApplicationService cut = new CreditApplicationService(new PersonScoringCalculator(new IncomeCalculator(), new EducationCalculator(), new MaritalStatusCalculator()), new CreditRatingCalculator());

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

        Person person = Person.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
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
        assertEquals(600, decisionType.getScoring());
        assertEquals(new BigDecimal(360000.0).setScale(2),decisionType.getCreditRating());
    }
}
