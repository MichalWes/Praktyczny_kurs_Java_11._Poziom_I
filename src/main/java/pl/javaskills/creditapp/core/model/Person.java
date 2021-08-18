package pl.javaskills.creditapp.core.model;

public class Person {
    private PersonalData personalData;
    private FinanceData financeData;
    private ContactData contactData;

    public Person(PersonalData personalData, FinanceData financeData, ContactData contactData) {
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
