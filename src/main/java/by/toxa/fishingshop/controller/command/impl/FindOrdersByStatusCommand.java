package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.Router;
import by.toxa.fishingshop.model.entity.Order;
import by.toxa.fishingshop.model.entity.OrderStatus;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindOrderByOrderStatusSpecification;
import by.toxa.fishingshop.model.service.impl.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.ERROR_PAGE;
import static by.toxa.fishingshop.controller.command.PagePath.ORDERS_PAGE;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.RequestAttribute.EXCEPTION;
import static by.toxa.fishingshop.controller.command.RequestParameter.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;

public class FindOrdersByStatusCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        OrderStatus status = OrderStatus.valueOf(request.getParameter(ORDER_STATUS).toUpperCase());
        OrderService service = OrderService.getInstance();
        try {
            List<Order> orderList = service.query(new FindOrderByOrderStatusSpecification(status));
            request.setAttribute(ORDER_LIST, orderList);
            router = new Router(ORDERS_PAGE, FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}