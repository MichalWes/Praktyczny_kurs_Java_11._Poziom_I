package pl.javaskills.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Guarantor;

import java.util.Set;

public class GuarantorsCalculator implements ScoringCalculator{

    private static final Logger log = LoggerFactory.getLogger(GuarantorsCalculator.class);

    @Override
    public int calculate(CreditApplication creditApplication) {
        int score = 0;
        Set<Guarantor> guarantors = creditApplication.getGuarantors();
        for (Guarantor guarantor : guarantors){
            if (guarantor.getAge() < 40){
                score += 50;
                log.info("Additional points for guarantor below 40 yo (+50 points)");
            } else {
                score += 25;
                log.info("Additional points for guarantor above 40 yo (+25 points)");
            }
        }
        if (score > 0) log.info("Total score for guarantors (+"+score+" points)");
        return score;
    }
}
