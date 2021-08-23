package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.*;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindPurchasesByOrderIdSpecification;
import by.toxa.fishingshop.model.service.ClearCartService;
import by.toxa.fishingshop.model.service.impl.ClearCartServiceImpl;
import by.toxa.fishingshop.model.service.impl.OrderService;
import by.toxa.fishingshop.model.service.impl.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.PagePath.ERROR_PAGE;
import static by.toxa.fishingshop.controller.command.RequestAttribute.EXCEPTION;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;
import static by.toxa.fishingshop.controller.command.Router.RouterType.REDIRECT;

public class ChangeOrderStatusCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        long orderId = Long.parseLong(request.getParameter(RequestParameter.ID));
        OrderStatus orderStatus = OrderStatus.valueOf(request.getParameter(RequestParameter.ORDER_STATUS).toUpperCase());
        Order order = new Order.OrderBuilder()
                .setId(orderId)
                .setOrderStatus(orderStatus)
                .build();
        try {
            if (orderStatus == OrderStatus.CANCELED) {
                PurchaseService purchaseService = PurchaseService.getInstance();
                List<Purchase> purchases = purchaseService.query(new FindPurchasesByOrderIdSpecification(orderId));
                if (!purchases.isEmpty()) {
                    Purchase purchase = purchases.get(0);
                    Cart cart = purchase.getCart();
                    ClearCartService clearCartService = ClearCartServiceImpl.getInstance();
                    clearCartService.clearCart(cart);
                    OrderService orderService = OrderService.getInstance();
                    orderService.update(order);
                }
            } else {
                OrderService orderService = OrderService.getInstance();
                orderService.update(order);
            }
            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute(SessionAttribute.USER);
            switch (user.getRole()) {
                case USER:
                    router = new Router(GO_TO_START_PAGE, REDIRECT);
                    break;
                case MANAGER:
                    router = new Router(GO_TO_MANAGER_PAGE, REDIRECT);
                    break;
                default:
                    router = new Router(ERROR_404_PAGE, REDIRECT);
                    break;
            }
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
