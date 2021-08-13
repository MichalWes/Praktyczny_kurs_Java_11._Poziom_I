package pl.javaskills.creditapp.core;

import pl.javaskills.creditapp.core.model.Education;
import pl.javaskills.creditapp.core.model.MaritalStatus;
import pl.javaskills.creditapp.core.model.Person;

public class PersonScoringCalculator {
    public int calculate(Person person){
        int score = (int) person.getPersonalData().getTotalMonthlyIncomePLN()/person.getPersonalData().getNumOfFamilyDependants()/1000;
        score = score * 100;
        int marrScore = person.getPersonalData().getMaritalStatus().getScore();
        int edScore = person.getPersonalData().getEducation().getScore();

        score = score + marrScore + edScore;

        System.out.println(score);
        return score;
    }
}
