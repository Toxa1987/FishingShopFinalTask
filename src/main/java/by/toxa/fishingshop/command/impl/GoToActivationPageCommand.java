package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.*;
import by.toxa.fishingshop.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.repository.impl.FindByLoginSpecification;
import by.toxa.fishingshop.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;

public class GoToActivationPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        UserService service = UserService.getInstance();
        String login = request.getParameter(RequestParameter.LOGIN);
        try {
            User user = service.query(new FindByLoginSpecification(login)).get(0);
            request.setAttribute(RequestAttribute.USER, user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new Router(PagePath.ACTIVATION_PAGE, Router.RouterType.FORWARD);
    }
}
