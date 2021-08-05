package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.Command;
import by.toxa.fishingshop.command.PagePath;
import by.toxa.fishingshop.command.Router;

import javax.servlet.http.HttpServletRequest;

public class GoToEditProductPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.EDIT_PRODUCT,Router.RouterType.REDIRECT);
    }
}
