package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.javaskills.creditapp.core.model.*;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class MaritalStatusCalculatorTest {
    private MaritalStatusCalculator cut = new MaritalStatusCalculator();

    @ParameterizedTest
    @DisplayName("Should return point attached to enum element")
    @EnumSource(MaritalStatus.class)
    public void test1(MaritalStatus maritalStatus){
        //given
        Person person = PersonTestFactory.create(maritalStatus);
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
        CreditApplication creditApplication = new CreditApplication(person, null, guarantors);
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(maritalStatus.getScore(), scoring);
    }
}