package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.Router;
import by.toxa.fishingshop.model.entity.*;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindByIdSpecification;
import by.toxa.fishingshop.model.repository.impl.specification.FindOrderByOrderIdSpecification;
import by.toxa.fishingshop.model.repository.impl.specification.FindPurchasesByOrderIdSpecification;
import by.toxa.fishingshop.model.service.impl.OrderService;
import by.toxa.fishingshop.model.service.impl.PurchaseService;
import by.toxa.fishingshop.model.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.RequestParameter.ID;
import static by.toxa.fishingshop.controller.command.RequestParameter.USER_ID;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;
import static by.toxa.fishingshop.controller.command.Router.RouterType.REDIRECT;
import static by.toxa.fishingshop.controller.command.SessionAttribute.USER;

public class GetOrderDataCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        long orderId = Long.parseLong(request.getParameter(ID));
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(USER);
        Role role = user.getRole();
        OrderService orderService = OrderService.getInstance();
        PurchaseService purchaseService = PurchaseService.getInstance();
        try {
            Order order = orderService.query(new FindOrderByOrderIdSpecification(orderId)).get(0);
            Purchase purchase = purchaseService.query(new FindPurchasesByOrderIdSpecification(orderId)).get(0);
            List<Product> productList = purchase.getCart().getProducts();
            request.setAttribute(PRODUCT_LIST, productList);
            request.setAttribute(ORDER, order);
            switch (role) {
                case USER:
                    router = new Router(ORDER_DATA_PAGE, FORWARD);
                    break;
                case MANAGER:
                    UserService userService = UserService.getInstance();
                    long userId = Long.parseLong(request.getParameter(USER_ID));
                    User userByOrder = userService.query(new FindByIdSpecification(userId)).get(0);
                    request.setAttribute(ORDER_USER, userByOrder);
                    router = new Router(ORDER_DATA_PAGE, FORWARD);
                    break;
                default:
                    router = new Router(INDEX, REDIRECT);
            }
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
