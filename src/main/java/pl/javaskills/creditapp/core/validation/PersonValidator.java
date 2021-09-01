package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.LoanApplication;

public class PersonValidator implements Validator{
    private final PersonalDataValidator personalDataValidator;
    private final ContactDataValidator contactDataValidator;
    private final FinanceDataValidator financeDataValidator;


    public PersonValidator(PersonalDataValidator personalDataValidator, ContactDataValidator contactDataValidator, FinanceDataValidator financeDataValidator) {
        this.personalDataValidator = personalDataValidator;
        this.contactDataValidator = contactDataValidator;
        this.financeDataValidator = financeDataValidator;
    }

    @Override
    public void validate(LoanApplication loanApplication) throws ValidationException {
        ValidationUtils.validateNotNull("Personal Data", loanApplication.getPerson().getPersonalData());
        personalDataValidator.validate(loanApplication);
        ValidationUtils.validateNotNull("Contact Data", loanApplication.getPerson().getContactData());
        contactDataValidator.validate(loanApplication);
        ValidationUtils.validateNotNull("Finance Data", loanApplication.getPerson().getFinanceData());
        financeDataValidator.validate(loanApplication);
    }
}
