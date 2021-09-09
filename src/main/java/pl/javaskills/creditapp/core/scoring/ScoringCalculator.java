package pl.javaskills.creditapp.core.scoring;

import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.NaturalPerson;
import pl.javaskills.creditapp.core.model.SelfEmployed;

public interface ScoringCalculator {

    default int calculate(CreditApplication creditApplication){
        if(creditApplication.getPerson() instanceof SelfEmployed){
            return calculate((SelfEmployed)creditApplication.getPerson());
        }
        else if(creditApplication.getPerson() instanceof NaturalPerson) {
            return calculate((NaturalPerson)creditApplication.getPerson());
        }
        return 0;
    }

    default int calculate(SelfEmployed selfEmployed){
        return 0;
    }

    default int calculate(NaturalPerson naturalPerson){
        return 0;
    }
}
