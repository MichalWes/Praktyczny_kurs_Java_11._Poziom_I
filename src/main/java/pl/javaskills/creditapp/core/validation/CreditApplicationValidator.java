package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;

public class CreditApplicationValidator implements Validator{
    private final PersonValidator personValidator;
    private final PurposeOfLoanValidator purposeOfLoanValidator;
    private final GuarantorValidator guarantorValidator;

    public CreditApplicationValidator(PersonValidator personValidator, PurposeOfLoanValidator purposeOfLoanValidator, GuarantorValidator guarantorValidator) {
        this.personValidator = personValidator;
        this.purposeOfLoanValidator = purposeOfLoanValidator;
        this.guarantorValidator = guarantorValidator;
    }

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        ValidationUtils.validateNotNull("Person", creditApplication.getPerson());
        personValidator.validate(creditApplication);
        ValidationUtils.validateNotNull("Purpose of loan", creditApplication.getPurposeOfLoan());
        purposeOfLoanValidator.validate(creditApplication);
        guarantorValidator.validate(creditApplication);

    }
}
