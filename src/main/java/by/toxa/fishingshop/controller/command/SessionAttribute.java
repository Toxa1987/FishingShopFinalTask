package by.toxa.fishingshop.controller.command;

/**
 * Describes all used session attributes
 *
 * @author Anton Pysk
 */

public final class SessionAttribute {
    public static final String USER = "user";
    public static final String NOT_AUTHENTICATED = "not_authenticated";
    public static final String AUTHENTICATED = "authenticated";
    public static final String CART = "cart";
    public static final String LOCALE = "locale";
    public static final String PREVIOUS_REQUEST = "prev_request";

    private SessionAttribute() {
    }
}
