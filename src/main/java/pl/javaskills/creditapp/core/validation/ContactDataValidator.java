package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.ContactData;
import pl.javaskills.creditapp.core.model.LoanApplication;
import pl.javaskills.creditapp.core.model.PersonalData;

import static pl.javaskills.creditapp.core.Constants.*;

public class ContactDataValidator implements Validator {
    @Override
    public void validate(LoanApplication loanApplication) throws ValidationException {
        ContactData contactData = loanApplication.getPerson().getContactData();

        ValidationUtils.validateNotNull("Home Address", contactData.getHomeAddress());
        ValidationUtils.validateNotNull("Correspondence Address", contactData.getCorrespondenceAddress());
        ValidationUtils.validateNotNull("Email", contactData.getEmail());
        ValidationUtils.validateRegex("Email", contactData.getEmail(), EMAIL_REGEX);
        ValidationUtils.validateNotNull("Phone Number", contactData.getPhoneNumber());
        ValidationUtils.validateRegex("Phone Number", contactData.getPhoneNumber(), PHONE_NUMBER_REGEX);

    }
}
