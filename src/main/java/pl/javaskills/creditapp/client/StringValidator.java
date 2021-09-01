package pl.javaskills.creditapp.client;

public class StringValidator {
    public static boolean validate(String input, String regex1, String regex2) {
        if (input.matches(regex1) && input.matches(regex2)) {
            return true;
        }
        return false;
    }

    public static boolean validate(String input, String regex) {
        if (input.matches(regex)) {
            return true;
        }
        return false;
    }
}
