package pl.javaskills.creditapp.core.validation;

import pl.javaskills.creditapp.core.exception.*;

public class ValidationUtils {
    public static void validateNotNull(String field, Object object) throws ValidationException {
        if (object == null) {
            throw new NotNullException(field);
        }
    }

    public static void validateRegex(String field, String value, String regex) throws ValidationException {
        if (!value.matches(regex)) {
            throw new RegexException(field);
        }
    }

    public static void validateMinValue(String field, int expMinValue, int actMinValue) throws ValidationException {
        if (actMinValue < expMinValue) {
            throw new MinValueException(field, expMinValue);
        }
    }

    public static void validateMinValue(String field, double expMinValue, double actMinValue) throws ValidationException {
        if (actMinValue < expMinValue) {
            throw new MinValueException(field, expMinValue);
        }
    }

    public static void validateMaxValue(String field, int expMaxValue, int actMaxValue) throws ValidationException {
        if (actMaxValue > expMaxValue) {
            throw new MaxValueException(field, expMaxValue);
        }
    }

    public static void validateMaxValue(String field, double expMaxValue, double actMaxValue) throws ValidationException {
        if (actMaxValue > expMaxValue) {
            throw new MaxValueException(field, expMaxValue);
        }
    }
}
