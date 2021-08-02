package by.toxa.fishingshop.command;

public class PagePath {
    public static final String INDEX = "index.jsp";
    public static final String GO_TO_START_PAGE = "ApiController?command=start_page_command";
    public static final String GO_TO_ACTIVATION_PAGE = "?command=go_to_activation_page_command";
    public static final String LOGIN_PAGE = "pages/login.jsp";
    public static final String ERROR_PAGE = "/pages/error_pages/error.jsp";
    public static final String ERROR_404_PAGE = "/pages/error_pages/404.jsp";
    public static final String START_PAGE = "/pages/start.jsp";
    public static final String USERS_PAGE = "/pages/users.jsp";
    public static final String ADMIN_PAGE = "/pages/admin/admin.jsp";
    public static final String SIGN_UP_PAGE = "/pages/sign_up.jsp";
    public static final String INFORMATION_PAGE = "/pages/information.jsp";
    public static final String ACTIVATION_PAGE = "/pages/activation.jsp";
    public static final String EDIT_PRODUCT ="/pages/manager/edit_product.jsp" ;
    public static final String MANAGER_PAGE = "/pages/manager/manager.jsp" ;


    private PagePath() {
    }
}
