package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Person;

public class IncomeCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(IncomeCalculator.class);
    @Override
    public int calculate(CreditApplication creditApplication) {
        double incomePerFamilyMember = creditApplication.getPerson().getIncomePerFamilyMember();
        int score = (int) (incomePerFamilyMember/1000);
        score *= 100;
        log.info("Income per family member = "+incomePerFamilyMember+ScoringUtils.getPointsString(score));
        if (creditApplication.getPerson().getFinanceData().getSourcesOfIncome().size() > 1){
            score += 100;
            log.info("Extra points for more than one income: (+100 points)");
        }
        return score;
    }
}
