package pl.javaskills.creditapp.core.model;

public abstract class Person {
    private final PersonalData personalData;
    private final FinanceData financeData;
    private final ContactData contactData;

    protected Person(PersonalData personalData, FinanceData financeData, ContactData contactData) {
        this.personalData = personalData;
        this.financeData = financeData;
        this.contactData = contactData;
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
