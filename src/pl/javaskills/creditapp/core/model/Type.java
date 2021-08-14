package pl.javaskills.creditapp.core.model;

import pl.javaskills.creditapp.core.Constants;

public enum Type {
    MORTGAGE(Constants.MORTGAGE_LOAN_RATE),
    PERSONAL(Constants.PERSONAL_LOAN_LOAN_RATE);

    private final double rate;

    Type(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
