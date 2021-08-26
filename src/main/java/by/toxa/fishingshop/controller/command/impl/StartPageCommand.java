package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindAllProductsSpecification;
import by.toxa.fishingshop.model.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.toxa.fishingshop.controller.command.RequestAttribute.PRODUCT_LIST;
import static by.toxa.fishingshop.controller.command.Router.RouterType.*;

public class StartPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        ProductServiceImpl service = ProductServiceImpl.getInstance();
        HttpSession session = request.getSession(true);
        boolean notAuthenticated = (boolean) session.getAttribute(SessionAttribute.NOT_AUTHENTICATED);
        request.setAttribute(RequestAttribute.NOT_AUTHENTICATED, notAuthenticated);
        List<Product> productList = null;
        try {
            productList = service.query(new FindAllProductsSpecification());
            request.setAttribute(PRODUCT_LIST, productList);
            router = new Router(PagePath.START_PAGE, FORWARD);
        } catch (ServiceException e) {
            //log
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, FORWARD);
        }

        return router;
    }
}
