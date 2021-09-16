package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;
import pl.javaskills.creditapp.core.annotation.ValidateCollection;
import pl.javaskills.creditapp.core.annotation.ValidateObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Person {
    @NotNull
    @ValidateObject
    private final PersonalData personalData;
    @NotNull
    @ValidateObject
    private final FinanceData financeData;
    @NotNull
    @ValidateObject
    private final ContactData contactData;
    @NotNull
    @ValidateCollection
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
