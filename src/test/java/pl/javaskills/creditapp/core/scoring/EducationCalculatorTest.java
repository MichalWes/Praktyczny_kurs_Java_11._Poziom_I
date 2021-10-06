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

class EducationCalculatorTest {
    private EducationCalculator cut = new EducationCalculator();

    @ParameterizedTest
    @DisplayName("Should return point attached to enum element")
    @EnumSource(Education.class)
    public void test1(Education education) {
        //given
        Person person = PersonTestFactory.create(education);

        Guarantor guarantor1 = Guarantor.Builder
                .create()
                .withPesel("95222535353")
                .withBirthDate(LocalDate.of(1995, 12, 1))
                .build();

        Guarantor guarantor2 = Guarantor.Builder
                .create()
                .withPesel("95222535332")
                .withBirthDate(LocalDate.of(1975, 12, 1))
                .build();

        Set<Guarantor> guarantors = new TreeSet<>();
        guarantors.add(guarantor1);
        guarantors.add(guarantor2);
        CreditApplication creditApplication = new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE_ID, person, null, guarantors);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(education.getScore(), scoring);
    }

}