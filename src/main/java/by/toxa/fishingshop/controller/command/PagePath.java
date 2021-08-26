package by.toxa.fishingshop.controller.command;

/**
 * Describes all pages paths.
 *
 * @author Anton Pysk
 */
public final class PagePath {
    public static final String INDEX = "index.jsp";
    public static final String GO_TO_START_PAGE = "ApiController?command=start_page_command";
    public static final String GO_TO_ACTIVATION_PAGE = "ApiController?command=go_to_activation_page_command";
    public static final String GO_TO_ACTIVATION_PAGE_MAIl = "?command=go_to_activation_page_command";
    public static final String LOGIN_PAGE = "pages/login.jsp";
    public static final String ERROR_PAGE = "/pages/error_pages/error.jsp";
    public static final String ERROR_404_PAGE = "/pages/error_pages/404.jsp";
    public static final String START_PAGE = "/pages/start.jsp";
    public static final String USERS_PAGE = "/pages/users.jsp";
    public static final String ADMIN_PAGE = "/pages/admin/admin.jsp";
    public static final String SIGN_UP_PAGE = "/pages/sign_up.jsp";
    public static final String INFORMATION_PAGE = "/pages/information.jsp";
    public static final String ACTIVATION_PAGE = "/pages/activation.jsp";
    public static final String EDIT_PRODUCT = "/pages/manager/edit_product.jsp";
    public static final String MANAGER_PAGE = "/pages/manager/manager.jsp";
    public static final String PRODUCT_PAGE = "/pages/product.jsp";
    public static final String FIND_PRODUCTS_PAGE = "/pages/find_products.jsp";
    public static final String ORDERS_PAGE = "/pages/orders.jsp";
    public static final String ORDER_DATA_PAGE = "/pages/order_data.jsp";
    public static final String SEARCH_BY_VENDOR_PAGE = "/pages/manager/search_by_vendor.jsp";
    public static final String SEARCH_BY_PRODUCT_NAME_PAGE = "/pages/search_by_name.jsp";
    public static final String UPDATE_PRODUCT_PAGE = "/pages/manager/update_product.jsp";
    public static final String GO_TO_MANAGER_PAGE = "ApiController?command=go_to_manager_page_command";
    public static final String EDIT_MANAGER_PAGE = "/pages/admin/create_manager.jsp";
    public static final String GO_TO_ADMIN_PAGE = "ApiController?command=go_to_admin_page_command";
    public static final String SEARCH_BY_LOGIN_PAGE = "/pages/admin/search_by_login.jsp";
    public static final String USER_DATA_PAGE = "/pages/admin/user_data.jsp";
    public static final String BLOCKED_PAGE = "/pages/blocked.jsp";
    public static final String CONTROLLER_URL = "/ApiController?";

    private PagePath() {
    }
}
