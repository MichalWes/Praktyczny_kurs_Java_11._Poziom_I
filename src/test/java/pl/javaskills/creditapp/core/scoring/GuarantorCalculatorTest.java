package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.model.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuarantorCalculatorTest {
    private GuarantorsCalculator cut = new GuarantorsCalculator();

    @Test
    @DisplayName("Should return calculated guarantor score")
    public void test1(){
        //given
        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withFamilyMembers(new ArrayList<>())
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(FinanceData.Builder.create()
                        .withSourcesOfIncome(SourceOfIncome.Builder.create()
                                .withIncomeType(IncomeType.SELF_EMPLOYMENT)
                                .withNetMontlyIncome(10000.0)
                                .build())
                        .buildWithoutExpenses())
                .build();

        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder
                .create()
                .withType(Type.MORTGAGE)
                .withAmount(100000.0)
                .withPeriod((byte)30)
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
        CreditApplication creditApplication = CreditApplicationServiceTestFactory.create(person, purposeOfLoan, guarantors);

        //when
        int scoring = cut.calculate(creditApplication);

        //then
        assertEquals(75, scoring);
    }
}
