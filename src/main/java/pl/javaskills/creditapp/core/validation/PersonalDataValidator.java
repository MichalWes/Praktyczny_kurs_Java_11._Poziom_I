package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.PersonalData;

import static pl.javaskills.creditapp.core.Constants.*;

public class PersonalDataValidator implements Validator{
    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        PersonalData personalData = creditApplication.getPerson().getPersonalData();

        ValidationUtils.validateNotNull("Name",personalData.getName());
        ValidationUtils.validateRegex("Name",personalData.getName(), NAME_REGEX);
        ValidationUtils.validateNotNull("Last Name",personalData.getLastName());
        ValidationUtils.validateRegex("Last Name",personalData.getLastName(), LAST_NAME_REGEX);
        ValidationUtils.validateRegex("Last Name",personalData.getLastName(), LAST_NAME_REGEX2);
        ValidationUtils.validateNotNull("Mother's Maiden Name",personalData.getMothersMaidenName());
        ValidationUtils.validateRegex("Mother's Maiden Name",personalData.getMothersMaidenName(), LAST_NAME_REGEX);
        ValidationUtils.validateRegex("Mother's Maiden Name",personalData.getMothersMaidenName(), LAST_NAME_REGEX2);
        ValidationUtils.validateNotNull("Education",personalData.getEducation());
        ValidationUtils.validateNotNull("Marital Status",personalData.getMaritalStatus());
        ValidationUtils.validateMinValue("Number Of Family Dependants",1, creditApplication.getPerson().getFamilyMembers().size());

    }
}
