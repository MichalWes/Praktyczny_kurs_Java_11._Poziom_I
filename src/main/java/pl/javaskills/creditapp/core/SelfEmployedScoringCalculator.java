package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.SelfEmployed;
import pl.javaskills.creditapp.core.scoring.ScoringCalculator;

public class SelfEmployedScoringCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(SelfEmployedScoringCalculator.class);

    @Override
    public int calculate(SelfEmployed selfEmployed) {
        if (selfEmployed.getYearsSinceFounded() < 2) {
            log.info("Penalty of 200 points for young company");
            return -200;
        }
        return 0;
    }
}
