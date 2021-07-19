package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.*;
import by.toxa.fishingshop.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.repository.impl.FindByLoginSpecification;
import by.toxa.fishingshop.service.impl.UserService;
import by.toxa.fishingshop.service.validator.UserEmailValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.toxa.fishingshop.command.PagePath.SIGN_UP_PAGE;
import static by.toxa.fishingshop.command.Router.RouterType.FORWARD;

public class SingUpPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        UserService service = UserService.getInstance();
        String login = request.getParameter(RequestParameter.LOGIN);
        try {
            List<User> userList = service.query(new FindByLoginSpecification(login));
            if (userList.isEmpty()) {
                String email = request.getParameter(RequestParameter.EMAIL);
                if (UserEmailValidator.isValid(email)) {
                    HttpSession session = request.getSession(true);
                    User user = (User) session.getAttribute(SessionAttribute.USER);
                    user.setLogin(login);
                    service.insert(user);

                    router = new Router(PagePath.INFORMATION_PAGE,Router.RouterType.REDIRECT);
                } else {
                    request.setAttribute(RequestAttribute.INVALID_EMAIL, true);
                    router = new Router(SIGN_UP_PAGE, FORWARD);
                }
            } else {
                request.setAttribute(RequestAttribute.BOOKED_LOGIN, true);
                router = new Router(SIGN_UP_PAGE, FORWARD);
            }
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, FORWARD);
        }
        return null;
    }
}
