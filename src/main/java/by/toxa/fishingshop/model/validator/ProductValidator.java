package by.toxa.fishingshop.model.validator;

import java.io.InputStream;

public class ProductValidator {
    private static final String VENDOR_REGEX = "^[\\da-zA-Zа-яА-Я][\\da-zA-Zа-яА-Я-]{1,34}";
    private static final String NAME_REGEX = "^[a-zA-Zа-яА-Я][\\da-zA-Zа-яА-Я-\\s]{1,49}";
    private static final String PRICE_REGEX = "^(0|[1-9]\\d*)([.,]\\d{1,2})?";

    private ProductValidator() {
    }

    public static boolean isValidVendor(String vendor) {
        if (vendor == null || vendor.isEmpty() || vendor.trim().isEmpty()) {
            return false;
        }
        return vendor.matches(VENDOR_REGEX);
    }

    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty() || name.trim().isEmpty()) {
            return false;
        }
        return name.matches(NAME_REGEX);
    }

    public static boolean isValidPrice(String price) {
        if (price == null || price.isEmpty() || price.trim().isEmpty()) {
            return false;
        }
        return price.matches(PRICE_REGEX);
    }

    public static boolean isValidNumberInStock(int numberInStock) {
        return numberInStock >= 0;
    }

    public static boolean isValidImage(InputStream inputStream) {
        return inputStream != null;
    }
}

