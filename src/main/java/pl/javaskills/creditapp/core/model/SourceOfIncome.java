package pl.javaskills.creditapp.core.model;

public class SourceOfIncome {
    private IncomeType incomeType;
    private double netMontlyIncome;

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(IncomeType incomeType) {
        this.incomeType = incomeType;
    }

    public double getNetMontlyIncome() {
        return netMontlyIncome;
    }

    public void setNetMontlyIncome(double netMontlyIncome) {
        this.netMontlyIncome = netMontlyIncome;
    }
}
