package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.PersonTestFactory;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)

class PersonScoringCalculatorTest{
    @InjectMocks
    private NaturalPersonScoringCalculator cut1;

    @InjectMocks
    private SelfEmployedScoringCalculator cut2;

    @Mock
    private IncomeCalculator incomeCalculatorMock;
    @Mock
    private EducationCalculator educationCalculatorMock;
    @Mock
    private MaritalStatusCalculator maritalStatusCalculatorMock;

    @Test
    @DisplayName("Should return sum of calculations")
    public void test1(){
        //given
        Person person = PersonTestFactory.create();
        BDDMockito.given(incomeCalculatorMock.getIncomeScore(eq(person))).willReturn(50);
        BDDMockito.given(maritalStatusCalculatorMock.getMaritalStatusScore(eq(person))).willReturn(100);
        BDDMockito.given(educationCalculatorMock.getEducationScore(eq(person))).willReturn(200);
        //when
        int score = cut1.calculate(person);
        //then
        assertEquals(350,score);

        //when
        score = cut2.calculate(person);
        //then
        assertEquals(350,score);
    }
}

