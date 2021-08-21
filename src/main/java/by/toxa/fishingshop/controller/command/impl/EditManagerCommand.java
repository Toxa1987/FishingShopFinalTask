package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.Router;
import by.toxa.fishingshop.model.entity.Role;
import by.toxa.fishingshop.model.entity.Status;
import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindByLoginSpecification;
import by.toxa.fishingshop.model.service.impl.UserService;
import by.toxa.fishingshop.model.util.PasswordCodec;
import by.toxa.fishingshop.model.validator.UserEmailValidator;
import by.toxa.fishingshop.model.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.RequestParameter.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;

public class EditManagerCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        UserService service = UserService.getInstance();
        String login = request.getParameter(LOGIN);
        try {
            List<User> userList = service.query(new FindByLoginSpecification(login));
            if (userList.isEmpty()) {
                String email = request.getParameter(EMAIL);
                if (UserEmailValidator.isValid(email)) {
                    String password = request.getParameter(PASSWORD);
                    String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
                    if (password.equals(confirmPassword)) {
                        String lastName = request.getParameter(LAST_NAME);
                        String name = request.getParameter(NAME);
                        String phone = request.getParameter(PHONE);
                        if (!name.isEmpty() || !name.trim().isEmpty()) {
                            if (UserValidator.isValid(phone)) {
                                User user = new User.UserBuilder()
                                        .setLogin(login)
                                        .setPassword(PasswordCodec.getInstance().codeString(password, login))
                                        .setName(name)
                                        .setLastName(lastName)
                                        .setPhone(phone)
                                        .setEmail(email)
                                        .setRole(Role.MANAGER)
                                        .setStatus(Status.ACTIVATED)
                                        .build();
                                service.insert(user);
                                router = new Router(GO_TO_ADMIN_PAGE, REDIRECT);
                            } else {
                                request.setAttribute(INVALID_PHONE, true);
                                router = new Router(EDIT_MANAGER_PAGE, FORWARD);
                            }
                        } else {
                            if (!UserValidator.isValid(phone)) {
                                request.setAttribute(INVALID_PHONE, true);
                            }
                            request.setAttribute(NAME_IS_EMPTY, true);
                            router = new Router(EDIT_MANAGER_PAGE, FORWARD);
                        }
                    } else {
                        request.setAttribute(INVALID_PASSWORDS, true);
                        router = new Router(EDIT_MANAGER_PAGE, FORWARD);
                    }
                } else {
                    request.setAttribute(INVALID_EMAIL, true);
                    router = new Router(EDIT_MANAGER_PAGE, FORWARD);
                }
            } else {
                request.setAttribute(BOOKED_LOGIN, true);
                router = new Router(EDIT_MANAGER_PAGE, FORWARD);
            }
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
