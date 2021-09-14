package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Guarantor;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.PersonTestFactory;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class IncomeCalculatorTest {
    private IncomeCalculator cut = new IncomeCalculator();

    @Test
    @DisplayName("Should return calculated income score")
    public void test1(){
        //given
        Person person = PersonTestFactory.create(3000.0, 1000.0, 1000.0);
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
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(600, scoring);
    }
}