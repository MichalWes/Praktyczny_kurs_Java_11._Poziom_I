package pl.javaskills.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.SelfEmployed;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;

public class SelfEmployedScoringCalculator extends PersonScoringCalculator{
    private static final Logger log = LoggerFactory.getLogger(SelfEmployedScoringCalculator.class);

    public SelfEmployedScoringCalculator(IncomeCalculator incomeCalculator, EducationCalculator educationCalculator, MaritalStatusCalculator maritalStatusCalculator) {
        super(incomeCalculator, educationCalculator, maritalStatusCalculator);
    }

    @Override
    protected int addAdditionalPoints(Person person) {
        if (person instanceof SelfEmployed) {
            SelfEmployed selfEmployed = (SelfEmployed) person;
            if (selfEmployed.getYearsSinceFounded() < 2) {
                log.info("Penalty of 200 points for young company");
                return -200;
            }
        }
        return 0;
    }

}
