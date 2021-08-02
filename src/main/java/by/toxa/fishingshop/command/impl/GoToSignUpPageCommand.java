package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.Command;
import by.toxa.fishingshop.command.PagePath;
import by.toxa.fishingshop.command.Router;

import javax.servlet.http.HttpServletRequest;

import static by.toxa.fishingshop.command.Router.RouterType.REDIRECT;

public class GoToSignUpPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.SIGN_UP_PAGE,REDIRECT);
    }
}
