package pl.javaskills.creditapp.core.model;

public enum Education {
    PRIMARY(-100),
    MIDDLE(),
    SECONDARY(),
    TERTIARY(100),
    POST_SECONDARY(),
    NONE(-200);

    private int score = 0;

    Education() {
    }

    Education(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
