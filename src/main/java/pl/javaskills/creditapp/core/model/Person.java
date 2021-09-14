package pl.javaskills.creditapp.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Person {
    private final PersonalData personalData;
    private final FinanceData financeData;
    private final ContactData contactData;
    private final List<FamilyMember> familyMembers;


    protected Person(PersonalData personalData, FinanceData financeData, ContactData contactData, List<FamilyMember> familyMembers) {
        this.personalData = personalData;
        this.financeData = financeData;
        this.contactData = contactData;
        this.familyMembers = familyMembers;
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


    public double getBalance() {
        double expenses = 0.0;
        double balance = this.getFinanceData().getTotalIncome();

        if (financeData.getExpenses().isEmpty()) {
            return balance;
        } else {
            for (Expense expense : financeData.getExpenses()) {
                expenses += expense.getAmount();
            }
        }
        return balance - expenses;
    }

    public double getIncomePerFamilyMember() {
        return getBalance() / this.familyMembers.size();
    }

    public List<FamilyMember> getFamilyMembers() {
        Collections.sort(familyMembers);
        return familyMembers;
    }

    public List<FamilyMember> getFamilyMembersSortedByName() {
        List<FamilyMember> copy = new ArrayList<>(this.familyMembers);
        Collections.sort(copy, new FamilyMemberNameComparator());
        return copy;
    }

}
