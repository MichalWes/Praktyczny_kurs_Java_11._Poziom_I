package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.Constants;
import pl.javaskills.creditapp.core.annotation.NotNull;
import pl.javaskills.creditapp.core.annotation.Regex;

import java.util.List;

public class NaturalPerson extends Person {
    @NotNull
    @Regex(Constants.PESEL_REGEX)
    private final String pesel;

    private NaturalPerson(String pesel, PersonalData personalData, FinanceData financeData, ContactData contactData, List<FamilyMember> familyMembers) {
        super(personalData, financeData, contactData, familyMembers);
        this.pesel = pesel;
    }

    public static class Builder {
        private PersonalData personalData;
        private FinanceData financeData;
        private ContactData contactData;
        private List<FamilyMember> familyMembers;
        private String pesel;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withPersonalData(PersonalData personalData) {
            this.personalData = personalData;
            return this;
        }

        public Builder withFinanceData(FinanceData financeData) {
            this.financeData = financeData;
            return this;
        }

        public Builder withContactData(ContactData contactData) {
            this.contactData = contactData;
            return this;
        }

        public Builder withFamilyMembers(List<FamilyMember> familyMembers) {
            this.familyMembers = familyMembers;
            return this;
        }

        public Builder withPesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public NaturalPerson build() {
            return new NaturalPerson(pesel, personalData, financeData, contactData, familyMembers);
        }
    }

    public String getPesel() {
        return pesel;
    }
}