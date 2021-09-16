package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.annotation.NotNull;
import pl.javaskills.creditapp.core.annotation.ValidateCollection;

import java.util.*;

public class FinanceData {

    @NotNull
    @ValidateCollection
    private final List<SourceOfIncome> sourcesOfIncome;
    @NotNull
    @ValidateCollection
    private final Set<Expense> expenses;

    public FinanceData(List<SourceOfIncome> sourcesOfIncome, Set<Expense> expenses) {
        this.sourcesOfIncome = sourcesOfIncome;
        this.expenses = expenses;
    }

    public FinanceData(List<SourceOfIncome> sourcesOfIncome) {
        this.sourcesOfIncome = sourcesOfIncome;
        this.expenses = new HashSet<>();
    }

    public static class Builder {
        private List<SourceOfIncome> sourcesOfIncome;
        private Set<Expense> expenses;

        public static Builder create() {
            return new Builder();
        }

        public Builder withSourcesOfIncome(SourceOfIncome... sourcesOfIncome) {
            this.sourcesOfIncome = List.of(sourcesOfIncome); //Arrays.asList
            return this;
        }

        public Builder withExpenses(Set<Expense> expenses) {
            this.expenses = expenses;
            return this;
        }

        public FinanceData buildWithExpenses() {
            return new FinanceData(sourcesOfIncome, expenses);
        }

        public FinanceData buildWithoutExpenses() {
            return new FinanceData(sourcesOfIncome);
        }

    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public List<SourceOfIncome> getSourcesOfIncome() {
        return sourcesOfIncome;
    }

    public double getTotalIncome() {
        double totalincome = 0.0;
        for (SourceOfIncome sourcesOfIncome : sourcesOfIncome) {
            totalincome += sourcesOfIncome.getNetMontlyIncome();
        }
        return totalincome;
    }

    private Map<ExpenseType, Set<Expense>> getExpensesMap() {
        Map<ExpenseType, Set<Expense>> expensesMap = new HashMap<>();
        for (Expense expense : this.expenses) {
            if (!expensesMap.containsKey(expense.getType())) {
                expensesMap.put(expense.getType(), new HashSet<>());
                expensesMap.get(expense.getType()).add(expense);
            } else expensesMap.get(expense.getType()).add(expense);
        }
        return expensesMap;
    }

    public double getSumOfExpenses(ExpenseType type) {
        Map<ExpenseType, Set<Expense>> expensesMap = getExpensesMap();
        double totalExpenses = 0.0;
        if (expensesMap.isEmpty()){
            return totalExpenses;
        } else {
            for (Expense expense : expensesMap.get(type)) {
                totalExpenses += expense.getAmount();
            }
            return totalExpenses;
        }
    }
}
