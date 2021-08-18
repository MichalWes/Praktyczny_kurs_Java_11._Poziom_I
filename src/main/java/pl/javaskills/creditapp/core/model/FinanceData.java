package pl.javaskills.creditapp.core.model;

public class FinanceData {

    private SourceOfIncome[] sourcesOfIncome;

    public void addIncomeType(SourceOfIncome... sourcesOfIncome){
        this.sourcesOfIncome = sourcesOfIncome;
    }

    public SourceOfIncome[] getSourcesOfIncome() {
        return sourcesOfIncome;
    }

    public double getTotalIncome() {
        double totalincome=0.0;
        for(SourceOfIncome sourcesOfIncome : sourcesOfIncome){
            totalincome = totalincome + sourcesOfIncome.getNetMontlyIncome();
        }
        return totalincome;
    }
}
