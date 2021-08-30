package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.PersonTestFactory;
import pl.javaskills.creditapp.core.scoring.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class CompoundScoringCalculatorTest {

    private PersonCalculator incomeCalculatorMock = Mockito.mock(PersonCalculator.class);
    private PersonCalculator educationCalculatorMock = Mockito.mock(PersonCalculator.class);
    private PersonCalculator maritalStatusCalculatorMock = Mockito.mock(PersonCalculator.class);
    private CompoundScoringCalculator cut = new CompoundScoringCalculator(incomeCalculatorMock, educationCalculatorMock, maritalStatusCalculatorMock);

    @Test
    @DisplayName("Should return sum of calculations")
    public void test1() {
        //given
        Person person = PersonTestFactory.create();
        BDDMockito.given(incomeCalculatorMock.calculate(eq(person))).willReturn(50);
        BDDMockito.given(maritalStatusCalculatorMock.calculate(eq(person))).willReturn(100);
        BDDMockito.given(educationCalculatorMock.calculate(eq(person))).willReturn(200);

        //when
        int score = cut.calculate(person);
        //then
        assertEquals(350, score);
    }
}

