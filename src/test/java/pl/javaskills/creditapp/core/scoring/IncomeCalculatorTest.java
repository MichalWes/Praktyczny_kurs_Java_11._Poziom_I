package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.PersonTestFactory;

import static org.junit.jupiter.api.Assertions.*;

class IncomeCalculatorTest {
    private IncomeCalculator cut = new IncomeCalculator();

    @Test
    @DisplayName("Should return calculated income score")
    public void test1(){
        //given
        Person person = PersonTestFactory.create(3000.0, 1000.0, 1000.0);
        CreditApplication creditApplication = new CreditApplication(person, null, null);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(600, scoring);
    }
}