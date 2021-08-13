package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.Person;

public class CreditApplicationService {
    public String getDecision(Person person){
        int score = new PersonScoringCalculator().calculate(person);

        String decision = score<300 ? "Sorry " + person.getPersonalData().getName() + " " + person.getPersonalData().getLastName() + " decision is negative" :
                "Congratulations, " + person.getPersonalData().getName() + " " + person.getPersonalData().getLastName() + " decision is positive";
        return decision;
    }

}