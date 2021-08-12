package pl.javaskills.creditapp.core;

public class PersonScoringCalculator {
    public int calculate(Person person){
        int score = (int) person.getTotalMonthlyIncomePLN()/person.getNumOfFamilyDependants()/1000;
        score = score * 100;
        int marrScore = (person.isMarried()) ? 100 : 0;
        score = score + marrScore;
        System.out.println(score);
        return score;
    }
}
