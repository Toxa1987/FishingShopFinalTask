package by.toxa.fishingshop.controller.command.impl.auth;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.controller.command.Router.RouterType;
import by.toxa.fishingshop.model.entity.Status;
import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindByLoginSpecification;
import by.toxa.fishingshop.model.service.MailSenderService;
import by.toxa.fishingshop.model.service.impl.MailSenderServiceImpl;
import by.toxa.fishingshop.model.service.impl.UserService;
import by.toxa.fishingshop.model.util.PasswordCodec;
import by.toxa.fishingshop.model.validator.UserEmailValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.PagePath.SIGN_UP_PAGE;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.RequestParameter.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;

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
                                request.getRequestURL()+
                                GO_TO_ACTIVATION_PAGE_MAIl +
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
