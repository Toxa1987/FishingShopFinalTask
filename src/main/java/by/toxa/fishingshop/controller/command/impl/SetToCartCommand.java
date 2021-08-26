package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.Cart;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindProductByIdSpecification;
import by.toxa.fishingshop.model.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.ERROR_PAGE;
import static by.toxa.fishingshop.controller.command.PagePath.GO_TO_START_PAGE;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;
import static by.toxa.fishingshop.controller.command.Router.RouterType.REDIRECT;
import static by.toxa.fishingshop.controller.command.SessionAttribute.*;

public class SetToCartCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        ProductServiceImpl service = ProductServiceImpl.getInstance();
        try {
            List<Product> productList = service.query(new FindProductByIdSpecification(id));
            HttpSession session = request.getSession(true);
            Cart cart = (Cart) session.getAttribute(CART);
            Product product = productList.get(0);
            int numberInStock = product.getNumberInStock();
            product.setNumberInStock(numberInStock - 1);
            cart.addProduct(product);
            service.update(product);
            router = new Router(GO_TO_START_PAGE, REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
