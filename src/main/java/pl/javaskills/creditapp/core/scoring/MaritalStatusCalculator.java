package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.MaritalStatus;
import pl.javaskills.creditapp.core.model.Person;


public class MaritalStatusCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(MaritalStatusCalculator.class);
    @Override
    public int calculate(CreditApplication creditApplication) {
        MaritalStatus maritalStatus= creditApplication.getPerson().getPersonalData().getMaritalStatus();
        int score = maritalStatus.getScore();
        log.info("Marital status = "+maritalStatus+ScoringUtils.getPointsString(score));
        return score;
    }
}

