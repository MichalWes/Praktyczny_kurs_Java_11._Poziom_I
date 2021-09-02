package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.ContactData;
import pl.javaskills.creditapp.core.model.CreditApplication;

import static pl.javaskills.creditapp.core.Constants.*;

public class ContactDataValidator implements Validator {
    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        ContactData contactData = creditApplication.getPerson().getContactData();

        ValidationUtils.validateNotNull("Home Address", contactData.getHomeAddress());
        ValidationUtils.validateNotNull("Correspondence Address", contactData.getCorrespondenceAddress());
        ValidationUtils.validateNotNull("Email", contactData.getEmail());
        ValidationUtils.validateRegex("Email", contactData.getEmail(), EMAIL_REGEX);
        ValidationUtils.validateNotNull("Phone Number", contactData.getPhoneNumber());
        ValidationUtils.validateRegex("Phone Number", contactData.getPhoneNumber(), PHONE_NUMBER_REGEX);

    }
}
