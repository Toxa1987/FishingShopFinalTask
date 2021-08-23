package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.RequestParameter;
import by.toxa.fishingshop.controller.command.Router;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindByVendorSpecification;
import by.toxa.fishingshop.model.service.impl.ProductService;

import javax.servlet.http.HttpServletRequest;

import static by.toxa.fishingshop.controller.command.PagePath.ERROR_PAGE;
import static by.toxa.fishingshop.controller.command.PagePath.PRODUCT_PAGE;
import static by.toxa.fishingshop.controller.command.RequestAttribute.EXCEPTION;
import static by.toxa.fishingshop.controller.command.RequestAttribute.PRODUCT;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;

public class SearchProductByVendorCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String vendor = request.getParameter(RequestParameter.VENDOR);
        ProductService service = ProductService.getInstance();
        try {
            Product product = service.query(new FindByVendorSpecification(vendor)).get(0);
            request.setAttribute(PRODUCT, product);
            router = new Router(PRODUCT_PAGE, FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}


