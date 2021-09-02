package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;

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
    public void validate(CreditApplication creditApplication) throws ValidationException {
        ValidationUtils.validateNotNull("Personal Data", creditApplication.getPerson().getPersonalData());
        personalDataValidator.validate(creditApplication);
        ValidationUtils.validateNotNull("Contact Data", creditApplication.getPerson().getContactData());
        contactDataValidator.validate(creditApplication);
        ValidationUtils.validateNotNull("Finance Data", creditApplication.getPerson().getFinanceData());
        financeDataValidator.validate(creditApplication);
    }
}
