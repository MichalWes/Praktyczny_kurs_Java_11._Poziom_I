package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.PurposeOfLoan;

public class PurposeOfLoanValidator implements Validator {

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        PurposeOfLoan purposeOfLoan = creditApplication.getPurposeOfLoan();
        ValidationUtils.validateNotNull("Type of Loan", purposeOfLoan.getType());
        ValidationUtils.validateNotNull("Amount", purposeOfLoan.getAmount());
        ValidationUtils.validateMinValue("Amount", 0.0, purposeOfLoan.getAmount());
        ValidationUtils.validateNotNull("Period", purposeOfLoan.getPeriod());
        ValidationUtils.validateMinValue("Min Period", 5, purposeOfLoan.getPeriod());
        ValidationUtils.validateMaxValue("Max Period", 30, purposeOfLoan.getPeriod());
    }
}
