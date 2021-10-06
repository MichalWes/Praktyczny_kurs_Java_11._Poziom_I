package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Guarantor;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.PersonTestFactory;
import pl.javaskills.creditapp.core.scoring.CompoundScoringCalculator;
import pl.javaskills.creditapp.core.scoring.ScoringCalculator;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static pl.javaskills.creditapp.core.Constants.CLIENT_TIME_ZONE_ID;
import static pl.javaskills.creditapp.core.Constants.DEFAULT_SYSTEM_LOCALE;

@ExtendWith(MockitoExtension.class)
class CompoundScoringCalculatorTest {

    private ScoringCalculator incomeCalculatorMock = Mockito.mock(ScoringCalculator.class);
    private ScoringCalculator educationCalculatorMock = Mockito.mock(ScoringCalculator.class);
    private ScoringCalculator maritalStatusCalculatorMock = Mockito.mock(ScoringCalculator.class);
    private CompoundScoringCalculator cut = new CompoundScoringCalculator(incomeCalculatorMock, educationCalculatorMock, maritalStatusCalculatorMock);

    @Test
    @DisplayName("Should return sum of calculations")
    public void test1() {
        //given
        Person person = PersonTestFactory.create();
        Guarantor guarantor1 = Guarantor.Builder
                .create()
                .withPesel("95222535353")
                .withBirthDate(LocalDate.of(1988, 12, 1))
                .build();

        Guarantor guarantor2 = Guarantor.Builder
                .create()
                .withPesel("95222535332")
                .withBirthDate(LocalDate.of(1988, 12, 1))
                .build();

        Set<Guarantor> guarantors = new TreeSet<>();
        guarantors.add(guarantor1);
        guarantors.add(guarantor2);
        CreditApplication creditApplication = new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE_ID, person, null, guarantors);
        BDDMockito.given(incomeCalculatorMock.calculate(eq(creditApplication))).willReturn(50);
        BDDMockito.given(maritalStatusCalculatorMock.calculate(eq(creditApplication))).willReturn(100);
        BDDMockito.given(educationCalculatorMock.calculate(eq(creditApplication))).willReturn(200);

        //when
        int score = cut.calculate(creditApplication);
        //then
        assertEquals(350, score);
    }
}

