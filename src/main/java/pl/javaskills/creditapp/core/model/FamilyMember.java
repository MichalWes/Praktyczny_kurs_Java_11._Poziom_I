package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;

import java.time.LocalDate;
import java.time.Period;

public class FamilyMember implements Comparable<FamilyMember> {
    @NotNull
    private final String name;
    @NotNull
    private final LocalDate birthDate;

    private FamilyMember(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public static class Builder {
        private String name;
        private LocalDate birthDate;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public FamilyMember build() {
            return new FamilyMember(name, birthDate);
        }

    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return name + ", " + getAge();
    }

    @Override
    public int compareTo(FamilyMember o) {
        return o.birthDate.compareTo(birthDate);
    }
}
