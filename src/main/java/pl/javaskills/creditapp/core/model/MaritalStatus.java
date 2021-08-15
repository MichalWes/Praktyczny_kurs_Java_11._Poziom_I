package pl.javaskills.creditapp.core.model;

public enum MaritalStatus {
    DIVORCED(),
    SEPARATED(100),
    SINGLE(),
    WIDOWED(),
    MARRIED(100);

    private int score = 0;

    MaritalStatus() {
    }

    MaritalStatus(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}

