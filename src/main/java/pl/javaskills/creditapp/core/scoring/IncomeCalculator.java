package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.Person;

public class IncomeCalculator {
    private static final Logger log = LoggerFactory.getLogger(IncomeCalculator.class);
    public int getIncomeScore(Person person) {
        double incomePerFamilyMember = person.getIncomePerFamilyMember();
        int score = (int) (incomePerFamilyMember/1000);
        score *= 100;
        log.info("Income per family member = "+incomePerFamilyMember+ScoringUtils.getPointsString(score));
        if (person.getFinanceData().getSourcesOfIncome().length > 1){
            score += 100;
            log.info("Extra points for more than one income: (+100 points)");
        }
        return score;
    }
}
