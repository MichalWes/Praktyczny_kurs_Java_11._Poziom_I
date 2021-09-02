package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.FinanceData;
import pl.javaskills.creditapp.core.model.CreditApplication;

public class FinanceDataValidator implements Validator {
    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        FinanceData financeData = creditApplication.getPerson().getFinanceData();

        ValidationUtils.validateNotNull("Total Income", financeData.getTotalIncome());
        ValidationUtils.validateMinValue("Min Total Income", 0, financeData.getTotalIncome());
        ValidationUtils.validateNotNull("Sources of income", financeData.getSourcesOfIncome());
    }
}
