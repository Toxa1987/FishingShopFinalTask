package by.toxa.fishingshop.controller.command.impl.go;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.PagePath;
import by.toxa.fishingshop.controller.command.RequestAttribute;
import by.toxa.fishingshop.controller.command.Router;
import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindAllUsersSpecification;
import by.toxa.fishingshop.model.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.toxa.fishingshop.controller.command.RequestAttribute.USER_LIST;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;
import static by.toxa.fishingshop.controller.command.Router.RouterType.REDIRECT;

public class GoToUsersPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        UserService service = UserService.getInstance();
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

        return router;
    }
}
