package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.Router;
import by.toxa.fishingshop.model.entity.Status;
import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindByIdSpecification;
import by.toxa.fishingshop.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.PagePath.ERROR_PAGE;
import static by.toxa.fishingshop.controller.command.RequestAttribute.EXCEPTION;
import static by.toxa.fishingshop.controller.command.RequestParameter.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;
import static by.toxa.fishingshop.controller.command.Router.RouterType.REDIRECT;

public class ChangeUserStatusCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        Status userStatus = Status.valueOf(request.getParameter(USER_STATUS).toUpperCase());
        long id = Long.parseLong(request.getParameter(ID));
        UserServiceImpl service = UserServiceImpl.getInstance();
        try {
            User user = service.query(new FindByIdSpecification(id)).get(0);
            user.setStatus(userStatus);
            service.update(user);
            router = new Router(GO_TO_ADMIN_PAGE, REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;

    }
}
