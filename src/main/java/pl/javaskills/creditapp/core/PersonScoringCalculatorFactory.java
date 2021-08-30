package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.NaturalPerson;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.SelfEmployed;
import pl.javaskills.creditapp.core.scoring.CompoundScoringCalculator;
import pl.javaskills.creditapp.core.scoring.EducationCalculator;
import pl.javaskills.creditapp.core.scoring.IncomeCalculator;
import pl.javaskills.creditapp.core.scoring.MaritalStatusCalculator;

public class PersonScoringCalculatorFactory {
    private final SelfEmployedScoringCalculator selfEmployedScoringCalculator;
    private final EducationCalculator educationCalculator;
    private final IncomeCalculator incomeCalculator;
    private final MaritalStatusCalculator maritalStatusCalculator;

    public PersonScoringCalculatorFactory(SelfEmployedScoringCalculator selfEmployedScoringCalculator, EducationCalculator educationCalculator, IncomeCalculator incomeCalculator, MaritalStatusCalculator maritalStatusCalculator) {
        this.selfEmployedScoringCalculator = selfEmployedScoringCalculator;
        this.educationCalculator = educationCalculator;
        this.incomeCalculator = incomeCalculator;
        this.maritalStatusCalculator = maritalStatusCalculator;
    }

    public CompoundScoringCalculator getCalculator(Person person) {
        if (person instanceof NaturalPerson){
            return new CompoundScoringCalculator(educationCalculator, incomeCalculator, maritalStatusCalculator);
        }else if (person instanceof SelfEmployed){
            return new CompoundScoringCalculator(educationCalculator, incomeCalculator, maritalStatusCalculator, selfEmployedScoringCalculator);
        }
        return null;
    }
}
