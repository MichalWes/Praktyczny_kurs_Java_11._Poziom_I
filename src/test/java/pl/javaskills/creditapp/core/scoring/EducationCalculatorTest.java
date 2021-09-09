package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Education;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.PersonTestFactory;

import static org.junit.jupiter.api.Assertions.*;

class EducationCalculatorTest {
    private EducationCalculator cut = new EducationCalculator();

    @ParameterizedTest
    @DisplayName("Should return point attached to enum element")
    @EnumSource(Education.class)
    public void test1(Education education){
        //given
        Person person = PersonTestFactory.create(education);
        CreditApplication creditApplication = new CreditApplication(person, null, null);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(education.getScore(), scoring);
    }

}