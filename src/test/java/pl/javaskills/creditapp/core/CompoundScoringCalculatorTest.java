package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.PersonTestFactory;
import pl.javaskills.creditapp.core.scoring.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

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
        CreditApplication creditApplication = new CreditApplication(person, null, null);
        BDDMockito.given(incomeCalculatorMock.calculate(eq(creditApplication))).willReturn(50);
        BDDMockito.given(maritalStatusCalculatorMock.calculate(eq(creditApplication))).willReturn(100);
        BDDMockito.given(educationCalculatorMock.calculate(eq(creditApplication))).willReturn(200);

        //when
        int score = cut.calculate(creditApplication);
        //then
        assertEquals(350, score);
    }
}

