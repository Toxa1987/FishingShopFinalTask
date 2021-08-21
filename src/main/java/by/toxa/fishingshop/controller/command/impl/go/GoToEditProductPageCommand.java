package by.toxa.fishingshop.controller.command.impl.go;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.PagePath;
import by.toxa.fishingshop.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

public class GoToEditProductPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.EDIT_PRODUCT,Router.RouterType.REDIRECT);
    }
}
