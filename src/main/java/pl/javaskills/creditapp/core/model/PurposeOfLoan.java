package pl.javaskills.creditapp.core.model;

public class PurposeOfLoan {
    private Type type;
    private double amount;
    private byte period;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public byte getPeriod() {
        return period;
    }

    public void setPeriod(byte period) {
        this.period = period;
    }
}


