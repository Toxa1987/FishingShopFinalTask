package by.toxa.fishingshop.validator;

public class UserPhoneValidator {
    private static final String PHONE_REGEX = "(\\+375)(29|25|44|33)[\\d]{7}";

    private UserPhoneValidator(){}

    public static boolean isValid(String phone){
        return phone.matches(PHONE_REGEX);
    }
}
