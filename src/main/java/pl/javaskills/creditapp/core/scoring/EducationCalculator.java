package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.Education;
import pl.javaskills.creditapp.core.model.Person;


public class EducationCalculator {
    private static final Logger log = LoggerFactory.getLogger(EducationCalculator.class);
    public int getEducationScore(Person person) {
        Education education = person.getPersonalData().getEducation();
        int score = education.getScore();
        log.info("Education = "+education+ScoringUtils.getPointsString(score));
        return score;
    }
}
