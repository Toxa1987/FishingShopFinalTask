package by.toxa.fishingshop.controller.command.impl.auth;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.RequestParameter;
import by.toxa.fishingshop.controller.command.Router;
import by.toxa.fishingshop.controller.command.Router.RouterType;
import by.toxa.fishingshop.controller.command.SessionAttribute;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.entity.Status;
import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.model.repository.impl.specification.FindByLoginSpecification;
import by.toxa.fishingshop.model.service.MailSenderService;
import by.toxa.fishingshop.model.service.impl.MailSenderServiceImpl;
import by.toxa.fishingshop.model.service.impl.UserService;
import by.toxa.fishingshop.model.util.PasswordCodec;
import by.toxa.fishingshop.model.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.RequestParameter.EMAIL;
import static by.toxa.fishingshop.controller.command.RequestParameter.LOGIN;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;

public class SignUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        UserService service = UserService.getInstance();
        String login = request.getParameter(LOGIN);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(SessionAttribute.USER);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setStatus(Status.NOT_ACTIVATED);
        if (UserValidator.isValidLogin(login)) {
            try {
                List<User> userList = service.query(new FindByLoginSpecification(login));
                if (userList.isEmpty()) {
                    String confirmPassword = request.getParameter(RequestParameter.CONFIRM_PASSWORD);
                    if (UserValidator.isIdenticalPasswords(password,confirmPassword)) {
                        if (service.insert(user)) {
                            MailSenderService mailSenderService = MailSenderServiceImpl.getInstance();
                            String requestURL = request.getRequestURL().toString();
                            String linkForActivation = createLink(login, requestURL);
                            mailSenderService.send(email, "Link for activation", linkForActivation);
                            router = new Router(INFORMATION_PAGE, RouterType.REDIRECT);
                        } else {
                            setRequestAttributes(request, user);
                            router = new Router(SIGN_UP_PAGE, FORWARD);
                        }
                    } else {
                        request.setAttribute(INVALID_PASSWORDS, true);
                        setRequestAttributes(request, user);
                        router = new Router(SIGN_UP_PAGE, FORWARD);
                    }
                } else {
                    request.setAttribute(BOOKED_LOGIN, true);
                    setRequestAttributes(request, user);
                    router = new Router(SIGN_UP_PAGE, FORWARD);
                }
            } catch (ServiceException e) {
                request.setAttribute(EXCEPTION, e);
                router = new Router(ERROR_PAGE, FORWARD);
            }
        } else {
            setRequestAttributes(request, user);
            router = new Router(SIGN_UP_PAGE, FORWARD);
        }
        return router;
    }

    private void setRequestAttributes(HttpServletRequest request, User user) {
        if (!UserValidator.isValidEmail(user.getEmail())) {
            request.setAttribute(INVALID_EMAIL, true);
            user.setEmail(null);
        }
        if (!UserValidator.isValidLogin(user.getLogin())) {
            request.setAttribute(INVALID_LOGIN, true);
            user.setLogin(null);
        }
        if (!UserValidator.isValidPassword(user.getPassword())) {
            request.setAttribute(INVALID_PASSWORD, true);
            user.setPassword(null);
        }
        request.setAttribute(USER, user);
    }

    private String createLink(String login, String requestUrl) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<a href=\"").append(requestUrl);
        stringBuffer.append(GO_TO_ACTIVATION_PAGE_MAIl).append("&");
        stringBuffer.append(LOGIN).append("=").append(login);
        stringBuffer.append("\">").append("Your link for activation.</a>");
        return stringBuffer.toString();
    }

}
