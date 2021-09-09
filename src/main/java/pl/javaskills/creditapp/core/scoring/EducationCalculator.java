package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Education;
import pl.javaskills.creditapp.core.model.Person;


public class EducationCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(EducationCalculator.class);

    @Override
    public int calculate(CreditApplication creditApplication) {
        Education education = creditApplication.getPerson().getPersonalData().getEducation();
        int score = education.getScore();
        log.info("Education = "+education+ScoringUtils.getPointsString(score));
        return score;
    }
}
