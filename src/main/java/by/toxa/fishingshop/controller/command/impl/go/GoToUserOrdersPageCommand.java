package by.toxa.fishingshop.controller.command.impl.go;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.Router;
import by.toxa.fishingshop.controller.command.SessionAttribute;
import by.toxa.fishingshop.model.entity.Order;
import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindOrderByUserIdSpecification;
import by.toxa.fishingshop.model.service.impl.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.ERROR_PAGE;
import static by.toxa.fishingshop.controller.command.PagePath.ORDERS_PAGE;
import static by.toxa.fishingshop.controller.command.RequestAttribute.EXCEPTION;
import static by.toxa.fishingshop.controller.command.RequestAttribute.ORDER_LIST;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;

public class GoToUserOrdersPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        OrderService service = OrderService.getInstance();
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(SessionAttribute.USER);
        try {
            List<Order> orderList = service.query(new FindOrderByUserIdSpecification(user.getId()));
            request.setAttribute(ORDER_LIST,orderList);
            router = new Router(ORDERS_PAGE,FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
