package pl.javaskills.creditapp.core.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.javaskills.creditapp.core.exception.RequirementNotMetCause;
import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.scoring.GuarantorsCalculator;
import pl.javaskills.creditapp.core.scoring.ScoringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static pl.javaskills.creditapp.core.model.ExpenseType.PERSONAL;

public class ExpensesPostValidator implements PostValidator {

    private static final Logger log = LoggerFactory.getLogger(ExpensesPostValidator.class);

    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException {
        double balance = creditApplication.getPerson().getBalance();
        log.info("Personal balance : " +  balance);
        double expensesPersonal = creditApplication.getPerson().getFinanceData().getSumOfExpenses(PERSONAL);
        log.info("Personal expenses amount : " +  expensesPersonal);
        double expensesPercentage = expensesPersonal / balance * 100;
        BigDecimal expensesPercentageBD = new BigDecimal(expensesPercentage).setScale(2, RoundingMode.HALF_DOWN);

        log.info("Expenses percentage: " +  expensesPercentageBD + "%");

        if (expensesPercentage > 40.0) {
            log.info("Too high expenses: " + expensesPercentageBD + "%");
            throw new RequirementNotMetException(RequirementNotMetCause.TOO_HIGH_PERSONAL_EXPENSES);
        }

    }
}
