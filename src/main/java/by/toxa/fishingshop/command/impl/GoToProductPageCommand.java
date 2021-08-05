package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.*;
import by.toxa.fishingshop.entity.Product;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.repository.impl.FindProductByIdSpecification;
import by.toxa.fishingshop.service.impl.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.toxa.fishingshop.command.PagePath.ERROR_PAGE;
import static by.toxa.fishingshop.command.Router.RouterType.FORWARD;

public class GoToProductPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        ProductService service = ProductService.getInstance();
        try {
            List<Product> productList = service.query(new FindProductByIdSpecification(id));
            request.setAttribute(RequestAttribute.PRODUCT, productList.get(0));
            router = new Router(PagePath.PRODUCT_PAGE, FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
