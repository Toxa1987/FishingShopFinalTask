package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.Command;
import by.toxa.fishingshop.command.PagePath;
import by.toxa.fishingshop.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        Router router = new Router(PagePath.INDEX, Router.RouterType.REDIRECT);
        return router;
    }
}
