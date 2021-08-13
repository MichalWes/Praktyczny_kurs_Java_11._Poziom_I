package pl.javaskills.creditapp.core;

public class PersonScoringCalculator {
    public int calculate(Person person){
        int score = (int) person.getPersonalData().getTotalMonthlyIncomePLN()/person.getPersonalData().getNumOfFamilyDependants()/1000;
        score = score * 100;
        int marrScore = ((person.getPersonalData().getMaritalStatus() == MaritalStatus.SEPARATED) ||
                (person.getPersonalData().getMaritalStatus() == MaritalStatus.MARRIED)
        ) ? 100 : 0;
        score = score + marrScore;
        int edScore = (person.getPersonalData().getEducation() == Education.NONE) ? -200 : 0;
        int edScore1 = (person.getPersonalData().getEducation() == Education.PRIMARY) ? -100 : 0;
        int edScore2 = (person.getPersonalData().getEducation() == Education.TERTIARY) ? 100 : 0;
        edScore = edScore + edScore1 + edScore2;
        score = score + edScore;

        System.out.println(score);
        return score;
    }
}
