package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.javaskills.creditapp.core.model.*;

import static org.junit.jupiter.api.Assertions.*;

class MaritalStatusCalculatorTest {
    private MaritalStatusCalculator cut = new MaritalStatusCalculator();

    @ParameterizedTest
    @DisplayName("Should return point attached to enum element")
    @EnumSource(MaritalStatus.class)
    public void test1(MaritalStatus maritalStatus){
        //given
        Person person = PersonTestFactory.create(maritalStatus);
        CreditApplication creditApplication = new CreditApplication(person, null, null);
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(maritalStatus.getScore(), scoring);
    }
}