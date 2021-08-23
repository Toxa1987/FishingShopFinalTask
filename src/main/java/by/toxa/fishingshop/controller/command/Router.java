package by.toxa.fishingshop.controller.command;


/**
 * Class Router which instance encapsulates page path and transition type to this page.
 *
 * @author Anton Pysk
 */
public final class Router {
    /**
     * The enum of transition types.
     */
    public enum RouterType {
        FORWARD,
        REDIRECT
    }

    private final String pagePath;
    private final RouterType routerType;

    public Router(String pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }

}
