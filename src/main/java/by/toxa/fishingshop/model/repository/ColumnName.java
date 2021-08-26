package by.toxa.fishingshop.model.repository;

/**
 * Describes all column name
 *
 * @author Anton Pysk
 */
public final class ColumnName {
    /*Users Table*/
    public static final String USERS_ID = "id";
    public static final String USERS_LOGIN = "login";
    public static final String USERS_EMAIL = "email";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_NAME = "name";
    public static final String USERS_LAST_NAME = "last_name";
    public static final String USERS_PHONE = "phone";
    public static final String USERS_ROLE = "role_id";
    public static final String USERS_STATUS = "status_id";

    /*Roles Table*/
    public static final String ROLES_ID = "id";
    public static final String ROLES_ROLE = "role";

    /*Status Table*/
    public static final String STATUS_ID = "id";
    public static final String STATUS_STATUS = "status";

    /*Products Table*/
    public static final String PRODUCTS_ID = "id";
    public static final String PRODUCTS_VENDOR = "vendor";
    public static final String PRODUCTS_NAME = "name";
    public static final String PRODUCTS_MANUFACTURE_ID = "manufacture_id";
    public static final String PRODUCTS_TYPE_ID = "type_id";
    public static final String PRODUCTS_DESCRIPTION = "description";
    public static final String PRODUCTS_IMAGE = "image";
    public static final String PRODUCTS_PRICE = "price";
    public static final String PRODUCTS_NUMBER_IN_STOCK = "number_in_stock";

    /*Product_types Table */
    public static final String PRODUCT_TYPES_ID = "id";
    public static final String PRODUCT_TYPES_TYPE = "type";
    public static final String PRODUCT_TYPES_DISCOUNT = "discount";

    /*Product_manufacture Table */
    public static final String PRODUCT_MANUFACTURE_ID = "id";
    public static final String PRODUCT_MANUFACTURE = "manufacture";

    /*Orders table*/
    public static final String ORDERS_ID = "id";
    public static final String ORDERS_ORDER = "order";
    public static final String ORDERS_DATE ="date";
    public static final String ORDERS_STATUS_ID = "order_status_id";
    public static final String ORDERS_SUMMARY_PRICE = "summary_price";
    public static final String ORDERS_USER_ID = "user_id";

    /*Order_status table*/
    public static final String ORDER_STATUS_ID = "id";
    public static final String ORDER_STATUS = "status";

    /*Purchases table*/
    public static final String PURCHASES_ID = "id";
    public static final String PURCHASES_ORDER_ID = "order_id";
    public static final String PURCHASES_PRODUCT_ID = "product_id";

    private ColumnName() {
    }
}
