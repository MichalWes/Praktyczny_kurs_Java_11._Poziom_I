package pl.javaskills.creditapp.core.model;

public class PurposeOfLoan {
    private Type type;
    private double amount;

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
}


