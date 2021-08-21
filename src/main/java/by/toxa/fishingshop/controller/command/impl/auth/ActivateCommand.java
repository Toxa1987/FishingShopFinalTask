package by.toxa.fishingshop.controller.command.impl.auth;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.Role;
import by.toxa.fishingshop.model.entity.Status;
import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.service.impl.UserService;
import by.toxa.fishingshop.model.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.RequestParameter.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.*;

public class ActivateCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        UserService service = UserService.getInstance();
        long id = Long.parseLong(request.getParameter(ID));
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        String lastName = request.getParameter(LAST_NAME);
        String name = request.getParameter(NAME);
        String phone = request.getParameter(PHONE);
        if (!name.isEmpty() || !name.trim().isEmpty()) {
            if (UserValidator.isValid(phone)) {
                User user = new User.UserBuilder()
                        .setId(id)
                        .setLogin(login)
                        .setPassword(password)
                        .setEmail(email)
                        .setName(name)
                        .setLastName(lastName)
                        .setPhone(phone)
                        .setRole(Role.USER)
                        .setStatus(Status.ACTIVATED)
                        .build();
                try {
                    service.update(user);
                    router = new Router(GO_TO_START_PAGE, REDIRECT);
                } catch (ServiceException e) {
                    request.setAttribute(EXCEPTION, e);
                    router = new Router(ERROR_PAGE, FORWARD);
                }
            } else {
                request.setAttribute(LOGIN, login);
                request.setAttribute(INVALID_PHONE, true);
                router = new Router(GO_TO_ACTIVATION_PAGE, FORWARD);
            }
        } else {
            request.setAttribute(LOGIN, login);
            if (!UserValidator.isValid(phone)) {
                request.setAttribute(INVALID_PHONE, true);
            }
            request.setAttribute(NAME_IS_EMPTY, true);
            router = new Router(GO_TO_ACTIVATION_PAGE, FORWARD);
        }
        return router;
    }
}
