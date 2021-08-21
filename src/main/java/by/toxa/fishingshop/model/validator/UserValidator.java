package by.toxa.fishingshop.model.validator;

public class UserValidator {
    private static final String PHONE_REGEX = "(\\+375)(29|25|44|33)[\\d]{7}";
    private static final String LOGIN_REGEX = "[a-zA-zА-Яа-я\\d]{4,20}";
    private static final String PASSWORD_REGEX = "[a-zA-zА-Яа-я\\d]{5,20}";
    private static final String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String NAME_REGEX = "[a-zA-Zа-я]{2,20}";
    private static final String LAST_NAME_REGEX = "[a-zA-Zа-я]{2,20}";

    private UserValidator() {
    }

    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValid(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    public static boolean isValidLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public static boolean isIdenticalPasswords(String password1, String password2) {
        return password1.equals(password2);
    }

    public static boolean isValidPassword(String password) {
        if (password.isEmpty() || password.trim().isEmpty()) return false;
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean isValidName(String name) {
        return name.matches(NAME_REGEX);
    }

    public static boolean isValidLastName(String lastName) {
        return lastName.matches(LAST_NAME_REGEX);
    }
}
