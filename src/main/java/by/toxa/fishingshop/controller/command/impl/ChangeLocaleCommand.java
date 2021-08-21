package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.RequestParameter;
import by.toxa.fishingshop.controller.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.toxa.fishingshop.controller.command.Router.RouterType.REDIRECT;
import static by.toxa.fishingshop.controller.command.SessionAttribute.LOCALE;
import static by.toxa.fishingshop.controller.command.SessionAttribute.PREVIOUS_REQUEST;

public class ChangeLocaleCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        String locale = request.getParameter(RequestParameter.LOCALE);
        HttpSession session = request.getSession(true);
        session.setAttribute(LOCALE, locale);
        String previousRequest = (String) session.getAttribute(PREVIOUS_REQUEST);
        return new Router(previousRequest, REDIRECT);
    }
}
