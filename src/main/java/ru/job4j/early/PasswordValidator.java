package ru.job4j.early;

public class PasswordValidator {
    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't to be is null");
        }
        if (password.length() < 8 || password.length() > 32) {
            return "Invalid password.";
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
        }
        boolean flagSubStr = false;
        String[] siqvence = {"qwerty", "12345", "password", "admin", "user"};
        for (String str : siqvence) {
            if (password.equalsIgnoreCase(str)) {
                flagSubStr = true;
            }
        }
        if (flagUpper && flagLower && flagDigit && flagSymbol && !flagSubStr) {
            return "Valid password.";
        }
        return "Invalid password.";
    }
}