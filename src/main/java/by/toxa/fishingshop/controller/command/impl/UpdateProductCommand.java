package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.ManufactureType;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.model.entity.ProductType;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.service.impl.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class UpdateProductCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        String vendor = request.getParameter(RequestParameter.VENDOR);
        String name = request.getParameter(RequestParameter.PRODUCT_NAME);
        String manufacture = request.getParameter(RequestParameter.PRODUCT_MANUFACTURE);
        String type = request.getParameter(RequestParameter.PRODUCT_TYPE);
        String description = request.getParameter(RequestParameter.PRODUCT_DESCRIPTION);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter(RequestParameter.PRODUCT_PRICE)));
        int numberInStock = Integer.parseInt(request.getParameter(RequestParameter.PRODUCT_NUMBER_IN_STOCK));
        Product product = new Product.ProductBuilder()
                .setId(id)
                .setVendor(vendor)
                .setName(name)
                .setManufacture(ManufactureType.valueOf(manufacture.toUpperCase()))
                .setType(ProductType.valueOf(type.toUpperCase()))
                .setDescription(description)
                .setPrice(price)
                .setNumberInStock(numberInStock)
                .build();
        ProductService service = ProductService.getInstance();
        try {
            service.update(product);
            router = new Router(PagePath.MANAGER_PAGE, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            //log
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
        return router;
    }
}
