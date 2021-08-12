package pl.javaskills.creditapp.core;

public class CreditApplicationService {
    public String getDecision(Person person){
        int score = new PersonScoringCalculator().calculate(person);

        String decision = score<300 ? "Sorry " + person.getName() + " " + person.getLastName() + " decision is negative" :
                "Congratulations, " + person.getName() + " " + person.getLastName() + " decision is positive";
        return decision;
    }

}