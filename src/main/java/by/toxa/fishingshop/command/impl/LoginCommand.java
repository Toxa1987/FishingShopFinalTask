package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.Command;
import by.toxa.fishingshop.command.RequestAttribute;
import by.toxa.fishingshop.command.Router;
import by.toxa.fishingshop.command.SessionAttribute;
import by.toxa.fishingshop.entity.Cart;
import by.toxa.fishingshop.entity.Role;
import by.toxa.fishingshop.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.toxa.fishingshop.command.PagePath.*;
import static by.toxa.fishingshop.command.RequestAttribute.WRONG_LOGIN_OR_PASSWORD;
import static by.toxa.fishingshop.command.Router.RouterType.FORWARD;
import static by.toxa.fishingshop.command.Router.RouterType.REDIRECT;

public class LoginCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService service = UserService.getInstance();
        try {
            Optional<User> optionalUser = service.authenticate(login, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                HttpSession session = request.getSession(true);
                session.setAttribute(SessionAttribute.USER, user);
                session.setAttribute(SessionAttribute.NOT_AUTHENTICATED, false);
                session.setAttribute(SessionAttribute.AUTHENTICATED,true);
                Role role = user.getRole();
                switch (role) {
                    case ADMIN: {
                        router = new Router(ADMIN_PAGE, REDIRECT);
                        break;
                    }
                    case USER: {
                        session.setAttribute(SessionAttribute.CART,new Cart());
                        router = new Router(GO_TO_START_PAGE, REDIRECT);
                        break;
                    }
                    case MANAGER: {
                        router = new Router(MANAGER_PAGE, REDIRECT);
                        break;
                    }
                    default: {
                        request.setAttribute(WRONG_LOGIN_OR_PASSWORD, true);
                        router = new Router(LOGIN_PAGE, FORWARD);
                    }
                }
            } else {
                request.setAttribute(WRONG_LOGIN_OR_PASSWORD, true);
                router = new Router(LOGIN_PAGE, FORWARD);
            }
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
