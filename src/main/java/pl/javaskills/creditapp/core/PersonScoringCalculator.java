package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.Person;

public class PersonScoringCalculator {
    public int calculate(Person person){
        int score = (int) person.getIncomePerFamilyMember()/1000;
        score = score * 100;
        int marrScore = person.getPersonalData().getMaritalStatus().getScore();
        int edScore = person.getPersonalData().getEducation().getScore();

        score = score + marrScore + edScore;

        return score;
    }
}
