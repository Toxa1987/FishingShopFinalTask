package by.toxa.fishingshop.controller.command.impl.auth;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.toxa.fishingshop.controller.command.PagePath.INDEX;
import static by.toxa.fishingshop.controller.command.Router.RouterType.REDIRECT;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        return new Router(INDEX, REDIRECT);
    }
}
