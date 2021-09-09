package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.CreditApplication;
import pl.javaskills.creditapp.core.model.Guarantor;

import java.util.Set;

public class GuarantorValidator implements Validator {
    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        Set<Guarantor> guarantors = creditApplication.getGuarantors();
        for (Guarantor guarantor : guarantors) {
            ValidationUtils.validateNotNull("Age", guarantor.getAge());
            ValidationUtils.validateMinValue("Minimal age", 0, guarantor.getAge());
            ValidationUtils.validateNotNull("Pesel", guarantor.getPesel());
            ValidationUtils.validateRegex("Pesel", guarantor.getPesel(), "\\d{11}");
        }
    }
}
