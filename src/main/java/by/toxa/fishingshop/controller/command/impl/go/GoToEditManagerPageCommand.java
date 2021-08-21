package by.toxa.fishingshop.controller.command.impl.go;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

import static by.toxa.fishingshop.controller.command.PagePath.EDIT_MANAGER_PAGE;
import static by.toxa.fishingshop.controller.command.Router.RouterType.REDIRECT;

public class GoToEditManagerPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(EDIT_MANAGER_PAGE,REDIRECT);
    }
}
