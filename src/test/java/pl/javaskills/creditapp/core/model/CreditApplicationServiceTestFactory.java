package pl.javaskills.creditapp.core.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static pl.javaskills.creditapp.core.Constants.CLIENT_TIME_ZONE_ID;
import static pl.javaskills.creditapp.core.Constants.DEFAULT_SYSTEM_LOCALE;

public class CreditApplicationServiceTestFactory {

    public static CreditApplication create(NaturalPerson person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        return new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE_ID, person, purposeOfLoan, guarantors);
    }

    public static CreditApplication create(NaturalPerson person, PurposeOfLoan purposeOfLoan) {
        return new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE_ID, person, purposeOfLoan);
    }

    public static CreditApplication create(SelfEmployed person, PurposeOfLoan purposeOfLoan) {
        return new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE_ID, person, purposeOfLoan);
    }

    public static CreditApplication create() {

        List<FamilyMember> familyMembers = new ArrayList<>();

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withBirthDate(LocalDate.of(2000, 12, 1))
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Zdzisław")
                .withBirthDate(LocalDate.of(1993, 12, 1))
                .build());

        NaturalPerson person = PersonTestFactory.create(3000.0, 1000.0, 1000.0, familyMembers, Education.MIDDLE, MaritalStatus.SEPARATED);
        return new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE_ID, person, null);

    }

    public static CreditApplication create(double amount) {

        List<FamilyMember> familyMembers = new ArrayList<>();

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withBirthDate(LocalDate.of(2000, 12, 1))
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Zdzisław")
                .withBirthDate(LocalDate.of(1993, 12, 1))
                .build());

        NaturalPerson person = PersonTestFactory.create(3000.0, 1000.0, 1000.0, familyMembers, Education.MIDDLE, MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder.create()
                .withAmount(amount)
                .build();
        return new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE_ID, person, purposeOfLoan);
    }


}
