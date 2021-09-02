package pl.javaskills.creditapp.core.model;

import java.util.List;

public class FinanceData {

    private final List<SourceOfIncome> sourcesOfIncome;

    private FinanceData(List<SourceOfIncome> sourcesOfIncome) {
        this.sourcesOfIncome = sourcesOfIncome;
    }

    public static class Builder {
        private List<SourceOfIncome> sourcesOfIncome;

        public static Builder create(){
            return new Builder();
        }

        public Builder withSourcesOfIncome(SourceOfIncome... sourcesOfIncome){
            this.sourcesOfIncome = List.of(sourcesOfIncome); //Arrays.asList
            return this;
        }

        public FinanceData build(){
            return new FinanceData(sourcesOfIncome);
        }
    }

    public List<SourceOfIncome> getSourcesOfIncome() {
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
