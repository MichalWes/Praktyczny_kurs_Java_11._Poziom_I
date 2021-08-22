package pl.javaskills.creditapp.core.model;

public class PurposeOfLoan {
    private Type type;
    private double amount;
    private byte period;

    private PurposeOfLoan(){
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public byte getPeriod() {
        return period;
    }

    public static class Builder {
        private Type type;
        private double amount;
        private byte period;

        private Builder(){
        }

        public static Builder create(){
            return new Builder();
        }

        public Builder withType(Type type){
            this.type = type;
            return this;
        }
        public Builder withAmount(double amount){
            this.amount = amount;
            return this;
        }
        public Builder withPeriod(byte period){
            this.period = period;
            return this;
        }

        public PurposeOfLoan build(){
            PurposeOfLoan purposeOfLoan = new PurposeOfLoan();
            purposeOfLoan.type = type;
            purposeOfLoan.amount = amount;
            purposeOfLoan.period = period;
            return purposeOfLoan;
        }
    }


}


