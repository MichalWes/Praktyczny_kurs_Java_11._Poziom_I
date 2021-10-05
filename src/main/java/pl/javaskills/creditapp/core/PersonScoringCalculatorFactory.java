package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.NaturalPerson;
import pl.javaskills.creditapp.core.model.Person;
import pl.javaskills.creditapp.core.model.SelfEmployed;
import pl.javaskills.creditapp.core.scoring.*;
import pl.javaskills.creditapp.di.Inject;

public class PersonScoringCalculatorFactory {
    @Inject
    private SelfEmployedScoringCalculator selfEmployedScoringCalculator;
    @Inject
    private EducationCalculator educationCalculator;
    @Inject
    private IncomeCalculator incomeCalculator;
    @Inject
    private MaritalStatusCalculator maritalStatusCalculator;
    @Inject
    private GuarantorsCalculator guarantorsCalculator;

    public PersonScoringCalculatorFactory(SelfEmployedScoringCalculator selfEmployedScoringCalculator, EducationCalculator educationCalculator, IncomeCalculator incomeCalculator, MaritalStatusCalculator maritalStatusCalculator, GuarantorsCalculator guarantorsCalculator) {
        this.selfEmployedScoringCalculator = selfEmployedScoringCalculator;
        this.educationCalculator = educationCalculator;
        this.incomeCalculator = incomeCalculator;
        this.maritalStatusCalculator = maritalStatusCalculator;
        this.guarantorsCalculator = guarantorsCalculator;
    }

    public PersonScoringCalculatorFactory(){
    }

    public CompoundScoringCalculator getCalculator(Person person) {
        if (person instanceof NaturalPerson){
            return new CompoundScoringCalculator(educationCalculator, incomeCalculator, maritalStatusCalculator, guarantorsCalculator);
        }else if (person instanceof SelfEmployed){
            return new CompoundScoringCalculator(educationCalculator, incomeCalculator, maritalStatusCalculator, guarantorsCalculator, selfEmployedScoringCalculator);
        }
        return null;
    }
}
