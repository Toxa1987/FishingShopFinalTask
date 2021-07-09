package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.Command;
import by.toxa.fishingshop.command.PagePath;
import by.toxa.fishingshop.command.RequestAttribute;
import by.toxa.fishingshop.command.Router;
import by.toxa.fishingshop.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.repository.impl.FindAllUsersSpecification;
import by.toxa.fishingshop.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.toxa.fishingshop.command.RequestAttribute.USER_LIST;
import static by.toxa.fishingshop.command.Router.RouterType.FORWARD;
import static by.toxa.fishingshop.command.Router.RouterType.REDIRECT;

public class GoToUsersPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        UserService service = new UserService();
        List<User> productList = null;
        try {
            productList = service.query(new FindAllUsersSpecification());
            request.setAttribute(USER_LIST,productList);
            router = new Router(PagePath.USERS_PAGE, FORWARD );
        } catch (ServiceException e) {
            //log
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, REDIRECT);
        }

        return null;
    }
}
