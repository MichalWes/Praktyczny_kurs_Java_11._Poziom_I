package pl.javaskills.creditapp.core.model;

public class SelfEmployed extends Person{
    private final String nip;
    private final String regon;

    private SelfEmployed(String nip, String regon, PersonalData personalData, FinanceData financeData, ContactData contactData) {
        super(personalData, financeData, contactData);
        this.nip = nip;
        this.regon = regon;
    }

    public static class Builder{
        private PersonalData personalData;
        private FinanceData financeData;
        private ContactData contactData;
        private String nip;
        private String regon;


        private Builder(){
        }

        public static Builder create(){
            return new Builder();
        }

        public Builder withPersonalData(PersonalData personalData){
            this.personalData = personalData;
            return this;
        }

        public Builder withFinanceData(FinanceData financeData){
            this.financeData = financeData;
            return this;
        }

        public Builder withContactData(ContactData contactData){
            this.contactData = contactData;
            return this;
        }

        public Builder withNip(String nip){
            this.nip = nip;
            return this;
        }

        public Builder withRegon(String regon){
            this.regon = regon;
            return this;
        }

        public SelfEmployed build(){
            return new SelfEmployed(nip, regon, personalData, financeData, contactData);
        }
    }
}
