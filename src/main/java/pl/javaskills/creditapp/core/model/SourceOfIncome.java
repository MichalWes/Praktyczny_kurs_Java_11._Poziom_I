package pl.javaskills.creditapp.core.model;

public class SourceOfIncome {
    private final IncomeType incomeType;
    private final double netMontlyIncome;

    private SourceOfIncome(IncomeType incomeType, double netMontlyIncome){
        this.incomeType = incomeType;
        this.netMontlyIncome = netMontlyIncome;
    }

    public static class Builder{
        private IncomeType incomeType;
        private double netMontlyIncome;

        public static Builder create(){
            return new Builder();
        }

        public Builder withIncomeType(IncomeType incomeType){
            this.incomeType = incomeType;
            return this;
        }

        public Builder withNetMontlyIncome(double netMontlyIncome){
            this.netMontlyIncome = netMontlyIncome;
            return this;
        }

        public SourceOfIncome build(){
            return new SourceOfIncome(incomeType, netMontlyIncome);
        }
    }



    public IncomeType getIncomeType() {
        return incomeType;
    }

    public double getNetMontlyIncome() {
        return netMontlyIncome;
    }

}
