package pl.javaskills.creditapp.core.model;

public class LoanApplication {

    private Person person;
    private PurposeOfLoan purposeOfLoan;

    public Person getPerson() {
        return person;
    }

    public PurposeOfLoan getPurposeOfLoan() {
        return purposeOfLoan;
    }

    private LoanApplication(Person person, PurposeOfLoan purposeOfLoan) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
    }

    public static class Builder{
        private Person person;
        private PurposeOfLoan purposeOfLoan;

        public static Builder create(){
            return new Builder();
        }

        public Builder withPerson(Person person){
            this.person = person;
            return this;
        }

        public Builder withPurposeOfLoan(PurposeOfLoan purposeOfLoan){
            this.purposeOfLoan = purposeOfLoan;
            return this;
        }

        public LoanApplication build(){
            return new LoanApplication(person, purposeOfLoan);
        }
    }


}
