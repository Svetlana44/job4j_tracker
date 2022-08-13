package ru.job4j.early;

public class PasswordValidator {
    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't to be is null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Invalid password. Length mast be less then 8 and more then 32.");
        }
        boolean flagUpper = false;
        boolean flagLower = false;
        boolean flagDigit = false;
        boolean flagSymbol = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                flagUpper = true;
            }
            if (Character.isLowerCase(password.charAt(i))) {
                flagLower = true;
            }
            if (Character.isDigit(password.charAt(i))) {
                flagDigit = true;

            }
            if (!Character.isLetterOrDigit(password.charAt(i))) {
                flagSymbol = true;
            }
            if (flagUpper && flagLower && flagDigit && flagSymbol) {
                break;
            }

        }
        String[] siqvence = {"qwerty", "12345", "password", "admin", "user"};
        for (String str : siqvence) {
            if (password.equalsIgnoreCase(str)) {
                throw new IllegalArgumentException("Password can't to contein one from substrings.");
            }
        }
        if (!flagUpper || !flagLower || !flagDigit || !flagSymbol) {
            throw new IllegalArgumentException("Invalid password.");
        }
        return "Valid password.";
    }
}