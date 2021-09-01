package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.ContactData;
import pl.javaskills.creditapp.core.model.FinanceData;
import pl.javaskills.creditapp.core.model.LoanApplication;

import static pl.javaskills.creditapp.core.Constants.EMAIL_REGEX;
import static pl.javaskills.creditapp.core.Constants.PHONE_NUMBER_REGEX;

public class FinanceDataValidator implements Validator {
    @Override
    public void validate(LoanApplication loanApplication) throws ValidationException {
        FinanceData financeData = loanApplication.getPerson().getFinanceData();

        ValidationUtils.validateNotNull("Total Income", financeData.getTotalIncome());
        ValidationUtils.validateMinValue("Min Total Income", 0, financeData.getTotalIncome());
        ValidationUtils.validateNotNull("Sources of income", financeData.getSourcesOfIncome());
    }
}
