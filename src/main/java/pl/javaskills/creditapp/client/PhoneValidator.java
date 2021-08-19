package pl.javaskills.creditapp.client;

import pl.javaskills.creditapp.core.Constants;

public class PhoneValidator {
    public static boolean validate(String input) {
        if (input.matches(Constants.PHONE_NUMBER_REGEX)) {
            return true;
        }
        return false;
    }
}
