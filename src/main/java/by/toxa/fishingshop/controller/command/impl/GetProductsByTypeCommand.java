package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.RequestParameter;
import by.toxa.fishingshop.controller.command.Router;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.model.entity.ProductType;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindProductsByTypeSpecification;
import by.toxa.fishingshop.model.service.impl.ProductService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.ERROR_PAGE;
import static by.toxa.fishingshop.controller.command.PagePath.FIND_PRODUCTS_PAGE;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;

public class GetProductsByTypeCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String type = request.getParameter(RequestParameter.PRODUCT_TYPE);
        ProductType productType = ProductType.valueOf(type.toUpperCase());
        ProductService service = ProductService.getInstance();
        try {
            List<Product> productList =  service.query(new FindProductsByTypeSpecification(productType));
            request.setAttribute(PRODUCT_LIST,productList);
            router = new Router(FIND_PRODUCTS_PAGE, FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
