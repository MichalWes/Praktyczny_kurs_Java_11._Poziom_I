package pl.javaskills.creditapp.core.model;

public class Person {
    private final PersonalData personalData;
    private final FinanceData financeData;
    private final ContactData contactData;

    protected Person(PersonalData personalData, FinanceData financeData, ContactData contactData) {
        this.personalData = personalData;
        this.financeData = financeData;
        this.contactData = contactData;
    }

    public static class Builder{
        private PersonalData personalData;
        private FinanceData financeData;
        private ContactData contactData;

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

        public Person build(){
            return new Person(personalData, financeData, contactData);
        }
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public FinanceData getFinanceData() {
        return financeData;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public double getIncomePerFamilyMember() {
        double incomePerFamilyMember = this.getFinanceData().getTotalIncome()/this.getPersonalData().getNumOfFamilyDependants();
        return incomePerFamilyMember;
    }

}
