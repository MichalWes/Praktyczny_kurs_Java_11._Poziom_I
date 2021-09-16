package pl.javaskills.creditapp.core.model;

import static pl.javaskills.creditapp.core.Constants.MORTGAGE_LOAN_RATE;
import static pl.javaskills.creditapp.core.Constants.PERSONAL_LOAN_LOAN_RATE;

public enum Type {
    MORTGAGE(MORTGAGE_LOAN_RATE),
    PERSONAL_LOAN(PERSONAL_LOAN_LOAN_RATE);

    private final double rate;

    Type(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
