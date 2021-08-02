package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.*;
import by.toxa.fishingshop.command.Router.RouterType;
import by.toxa.fishingshop.entity.Status;
import by.toxa.fishingshop.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.repository.impl.FindByLoginSpecification;
import by.toxa.fishingshop.service.MailSenderService;
import by.toxa.fishingshop.service.impl.MailSenderServiceImpl;
import by.toxa.fishingshop.service.impl.UserService;
import by.toxa.fishingshop.util.PasswordCodec;
import by.toxa.fishingshop.validator.UserEmailValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.toxa.fishingshop.command.PagePath.*;
import static by.toxa.fishingshop.command.PagePath.SIGN_UP_PAGE;
import static by.toxa.fishingshop.command.RequestAttribute.*;
import static by.toxa.fishingshop.command.RequestParameter.*;
import static by.toxa.fishingshop.command.Router.RouterType.FORWARD;

public class SignUpCommand implements Command {
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
                    String password = request.getParameter(RequestParameter.PASSWORD);
                    String confirmPassword = request.getParameter(RequestParameter.CONFIRM_PASSWORD);
                    if (password.equals(confirmPassword)) {
                        HttpSession session = request.getSession(true);
                        User user = (User) session.getAttribute(SessionAttribute.USER);
                        user.setLogin(login);
                        user.setEmail(email);
                        user.setPassword(PasswordCodec.getInstance().codeString(password,login));
                        user.setStatus(Status.NOT_ACTIVATED);
                        service.insert(user);
                        MailSenderService mailSenderService = MailSenderServiceImpl.getInstance();
                        String linkForActivation = "<a href=\"" +
                                request.getRequestURL() +
                                GO_TO_ACTIVATION_PAGE +
                                "&" +
                                LOGIN +
                                "=" +
                                login +
                                "\">" +
                                "Your link for activation.</a>";
                        mailSenderService.send(email, "Link for activation", linkForActivation);
                        router = new Router(INFORMATION_PAGE, RouterType.REDIRECT);
                    }else{
                        request.setAttribute(INVALID_PASSWORDS, true);
                        router = new Router(SIGN_UP_PAGE, FORWARD);
                    }
                } else {
                    request.setAttribute(INVALID_EMAIL, true);
                    router = new Router(SIGN_UP_PAGE, FORWARD);
                }
            } else {
                request.setAttribute(BOOKED_LOGIN, true);
                router = new Router(SIGN_UP_PAGE, FORWARD);
            }
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
