package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;

public abstract class PersonScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(PersonScoringCalculator.class);
    private final IncomeCalculator incomeCalculator;
    private final EducationCalculator educationCalculator;
    private final MaritalStatusCalculator maritalStatusCalculator;

    public PersonScoringCalculator(IncomeCalculator incomeCalculator, EducationCalculator educationCalculator, MaritalStatusCalculator maritalStatusCalculator) {
        this.incomeCalculator = incomeCalculator;
        this.educationCalculator = educationCalculator;
        this.maritalStatusCalculator = maritalStatusCalculator;
    }

    public int calculate(Person person){
        int incScore = incomeCalculator.getIncomeScore(person);
        int marrScore = maritalStatusCalculator.getMaritalStatusScore(person);
        int edScore = educationCalculator.getEducationScore(person);

        int score = incScore + marrScore + edScore + addAdditionalPoints(person);
        log.info("Calculated scoring = "+score+" points");
        return score;
    }
    protected abstract int addAdditionalPoints(Person person);
}
