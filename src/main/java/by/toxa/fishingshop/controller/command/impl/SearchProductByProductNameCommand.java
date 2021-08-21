package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindProductByNameSpecification;
import by.toxa.fishingshop.model.service.impl.ProductService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.PagePath.ERROR_PAGE;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.RequestAttribute.EXCEPTION;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;

public class SearchProductByProductNameCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String productName = request.getParameter(RequestParameter.PRODUCT_NAME);
        ProductService service = ProductService.getInstance();
        try {

            List<Product> productList = service.query(new FindProductByNameSpecification(productName));
            if (!productList.isEmpty()) {
                Product product = productList.get(0);
                request.setAttribute(PRODUCT, product);
            }else{
                request.setAttribute(RequestAttribute.FIND_NOTHING,true);
            }
            router = new Router(PRODUCT_PAGE, FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
