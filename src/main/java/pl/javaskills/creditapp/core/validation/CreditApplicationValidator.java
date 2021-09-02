package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;

public class CreditApplicationValidator implements Validator{
    private final PersonValidator personValidator;
    private final PurposeOfLoanValidator purposeOfLoanValidator;

    public CreditApplicationValidator(PersonValidator personValidator, PurposeOfLoanValidator purposeOfLoanValidator) {
        this.personValidator = personValidator;
        this.purposeOfLoanValidator = purposeOfLoanValidator;
    }

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        ValidationUtils.validateNotNull("Person", creditApplication.getPerson());
        personValidator.validate(creditApplication);
        ValidationUtils.validateNotNull("Purpose of loan", creditApplication.getPurposeOfLoan());
        purposeOfLoanValidator.validate(creditApplication);
    }
}
