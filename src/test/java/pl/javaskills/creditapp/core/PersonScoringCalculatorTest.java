package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.model.Education;
import pl.javaskills.creditapp.core.model.MaritalStatus;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.PersonTestFactory;

import static org.junit.jupiter.api.Assertions.*;

class PersonScoringCalculatorTest {
    private PersonScoringCalculator cut = new PersonScoringCalculator();

    @Test
    @DisplayName("Person Scoring Test 1")
    public void test1(){
        //given
        Person person = PersonTestFactory.create(5000, 2, Education.PRIMARY, MaritalStatus.MARRIED);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(200, scoring);
    }
    @Test
    @DisplayName("Person Scoring Test 2")
    public void test2(){
        //given
        Person person = PersonTestFactory.create(5500, 1, Education.MIDDLE, MaritalStatus.DIVORCED);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(500, scoring);
    }
    @Test
    @DisplayName("Person Scoring Test 3")
    public void test3(){
        //given
        Person person = PersonTestFactory.create(9000, 3, Education.NONE, MaritalStatus.SINGLE);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(100, scoring);
    }

}