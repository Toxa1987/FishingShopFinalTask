package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.Command;
import by.toxa.fishingshop.command.Router;
import by.toxa.fishingshop.command.SessionAttribute;
import by.toxa.fishingshop.entity.Role;
import by.toxa.fishingshop.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.toxa.fishingshop.command.PagePath.*;
import static by.toxa.fishingshop.command.Router.RouterType.FORWARD;
import static by.toxa.fishingshop.command.Router.RouterType.REDIRECT;

public class LoginPageCommand implements Command {
    private UserService service = UserService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            Optional<User> optionalUser = service.authentication(login, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                HttpSession session = request.getSession();
                session.setAttribute(SessionAttribute.USER,user);
                session.setAttribute(SessionAttribute.NOT_AUTHENTICATED,false);
                Role role = user.getRole();

                switch (role) {
                    case ADMIN: {
                        router = new Router(ADMIN_PAGE, REDIRECT);
                        break;
                    }
                    case USER: {
                        router = new Router(GO_TO_START_PAGE, FORWARD);
                        break;
                    }
                    case MANAGER: {
                        router = new Router(ADMIN_PAGE, REDIRECT);
                        break;
                    }
                    default: {
                        request.setAttribute("error", true);
                        router = new Router(LOGIN_PAGE, FORWARD);
                    }
                }

            } else {
                request.setAttribute("error", true);
                router = new Router(LOGIN_PAGE, FORWARD);
            }
        } catch (ServiceException e) {
            request.setAttribute("Exception", e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
