package pl.javaskills.creditapp.core.model;

import java.util.Optional;

public class LoanApplication {

    private final Optional<NaturalPerson> naturalPerson;
    private final Optional<SelfEmployed> selfEmployed;
    private final PurposeOfLoan purposeOfLoan;

   public LoanApplication(NaturalPerson naturalPerson, PurposeOfLoan purposeOfLoan) {
        this.naturalPerson = Optional.of(naturalPerson);
        this.selfEmployed = Optional.empty();
        this.purposeOfLoan = purposeOfLoan;
    }

    public LoanApplication(SelfEmployed selfEmployed, PurposeOfLoan purposeOfLoan) {
        this.selfEmployed = Optional.of(selfEmployed);
        this.naturalPerson = Optional.empty();
        this.purposeOfLoan = purposeOfLoan;
    }

    public Optional<NaturalPerson> getNaturalPerson() {
        return naturalPerson;
    }

    public Optional<SelfEmployed> getSelfEmployed() {
        return selfEmployed;
    }

    public PurposeOfLoan getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public Person getPerson() {
       if (naturalPerson.isPresent()){
           return naturalPerson.get();
       }
       return selfEmployed.get();
    }
}
