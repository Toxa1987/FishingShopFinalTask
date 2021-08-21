package by.toxa.fishingshop.controller.command.impl.go;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

import static by.toxa.fishingshop.controller.command.PagePath.SEARCH_BY_VENDOR_PAGE;
import static by.toxa.fishingshop.controller.command.Router.RouterType.REDIRECT;

public class GoToSearchProductByVendorPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(SEARCH_BY_VENDOR_PAGE, REDIRECT);
    }
}
