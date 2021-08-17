package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.model.Education;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.PersonTestFactory;

import static org.junit.jupiter.api.Assertions.*;

class IncomeCalculatorTest {
    private IncomeCalculator cut = new IncomeCalculator();

    @Test
    @DisplayName("Should return calculated income score")
    public void test1(){
        //given
        Person person = PersonTestFactory.create(5000.0, (byte)1);
        //when
        int scoring = cut.getIncomeScore(person);
        //then
        assertEquals(500, scoring);
    }
}