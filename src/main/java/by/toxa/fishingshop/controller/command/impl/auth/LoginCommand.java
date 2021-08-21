package by.toxa.fishingshop.controller.command.impl.auth;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.Cart;
import by.toxa.fishingshop.model.entity.Role;
import by.toxa.fishingshop.model.entity.Status;
import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.RequestAttribute.WRONG_LOGIN_OR_PASSWORD;
import static by.toxa.fishingshop.controller.command.RequestParameter.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;
import static by.toxa.fishingshop.controller.command.Router.RouterType.REDIRECT;
import static by.toxa.fishingshop.controller.command.SessionAttribute.*;

public class LoginCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        UserService service = UserService.getInstance();
        try {
            Optional<User> optionalUser = service.authenticate(login, password);
            if (optionalUser.isPresent()) {

                User user = optionalUser.get();
                if (user.getStatus() == Status.ACTIVATED) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute(USER, user);
                    session.setAttribute(NOT_AUTHENTICATED, false);
                    session.setAttribute(AUTHENTICATED, true);
                    Role role = user.getRole();
                    switch (role) {
                        case ADMIN: {
                            router = new Router(GO_TO_ADMIN_PAGE, REDIRECT);
                            break;
                        }
                        case USER: {
                            session.setAttribute(CART, new Cart());
                            router = new Router(GO_TO_START_PAGE, REDIRECT);
                            break;
                        }
                        case MANAGER: {
                            router = new Router(GO_TO_MANAGER_PAGE, REDIRECT);
                            break;
                        }
                        default: {
                            request.setAttribute(WRONG_LOGIN_OR_PASSWORD, true);
                            router = new Router(LOGIN_PAGE, FORWARD);
                        }
                    }
                } else {
                    router = new Router(BLOCKED_PAGE, FORWARD);
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
