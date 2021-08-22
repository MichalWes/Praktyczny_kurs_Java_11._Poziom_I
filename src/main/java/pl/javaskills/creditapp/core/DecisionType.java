package pl.javaskills.creditapp.core;

import java.math.BigDecimal;

public enum DecisionType {
    POSITIVE(),
    NEGATIVE_SCORING(),
    NEGATIVE_CREDIT_RATING(),
    CONTACT_REQUIRED(),
    NEGATIVE_REQUIREMENTS_NOT_MET();

    private BigDecimal creditRating;

    DecisionType() {
    }

    public BigDecimal getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(BigDecimal creditRating) {
        this.creditRating = creditRating;
    }
}
