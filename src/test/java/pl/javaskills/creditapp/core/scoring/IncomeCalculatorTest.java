package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Guarantor;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.PersonTestFactory;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.javaskills.creditapp.core.Constants.CLIENT_TIME_ZONE_ID;
import static pl.javaskills.creditapp.core.Constants.DEFAULT_SYSTEM_LOCALE;

class IncomeCalculatorTest {
    private IncomeCalculator cut = new IncomeCalculator();

    @Test
    @DisplayName("Should return calculated income score")
    public void test1() {
        //given
        Person person = PersonTestFactory.create(3000.0, 1000.0, 1000.0);
        Guarantor guarantor1 = Guarantor.Builder
                .create()
                .withPesel("95222535353")
                .withBirthDate(LocalDate.of(1988, 12, 1))
                .build();

        Guarantor guarantor2 = Guarantor.Builder
                .create()
                .withPesel("95222535332")
                .withBirthDate(LocalDate.of(1988, 12, 1))
                .build();

        Set<Guarantor> guarantors = new TreeSet<>();
        guarantors.add(guarantor1);
        guarantors.add(guarantor2);
        CreditApplication creditApplication = new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE_ID, person, null, guarantors);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(600, scoring);
    }
}