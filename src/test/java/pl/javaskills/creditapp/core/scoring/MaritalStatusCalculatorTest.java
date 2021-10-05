package pl.javaskills.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.javaskills.creditapp.core.model.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.javaskills.creditapp.core.Constants.CLIENT_TIME_ZONE_ID;
import static pl.javaskills.creditapp.core.Constants.DEFAULT_SYSTEM_LOCALE;

class MaritalStatusCalculatorTest {
    private MaritalStatusCalculator cut = new MaritalStatusCalculator();

    @ParameterizedTest
    @DisplayName("Should return point attached to enum element")
    @EnumSource(MaritalStatus.class)
    public void test1(MaritalStatus maritalStatus) {
        //given
        Person person = PersonTestFactory.create(maritalStatus);
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
        CreditApplication creditApplication = new CreditApplication(CLIENT_TIME_ZONE_ID, DEFAULT_SYSTEM_LOCALE, person, null, guarantors);
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(maritalStatus.getScore(), scoring);
    }
}