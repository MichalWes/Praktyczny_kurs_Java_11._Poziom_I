package pl.javaskills.creditapp.core.model;

public class FinanceData {

    private SourceOfIncome[] sourcesOfIncome;

    private FinanceData(SourceOfIncome[] sourcesOfIncome) {
        this.sourcesOfIncome = sourcesOfIncome;
    }

    public static class Builder {
        private SourceOfIncome[] sourcesOfIncome;

        public static Builder create(){
            return new Builder();
        }

        public Builder withSourcesOfIncome(SourceOfIncome... sourcesOfIncome){
            this.sourcesOfIncome = sourcesOfIncome;
            return this;
        }

        public FinanceData build(){
            return new FinanceData(sourcesOfIncome);
        }
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
