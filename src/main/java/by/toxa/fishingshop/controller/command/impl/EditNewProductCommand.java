package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.*;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.service.impl.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigDecimal;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.*;

public class EditNewProductCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String vendor = request.getParameter(RequestParameter.VENDOR);
        String name = request.getParameter(RequestParameter.PRODUCT_NAME);
        String manufacture = request.getParameter(RequestParameter.PRODUCT_MANUFACTURE);
        String type = request.getParameter(RequestParameter.PRODUCT_TYPE);
        String description = request.getParameter(RequestParameter.PRODUCT_DESCRIPTION);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter(RequestParameter.PRODUCT_PRICE)));
        int numberInStock = Integer.parseInt(request.getParameter(RequestParameter.PRODUCT_NUMBER_IN_STOCK));
        InputStream inputStream = null;
        try {
            for (Part part : request.getParts()) {
                inputStream = part.getInputStream();
            }
            Product product = new Product.ProductBuilder()
                    .setVendor(vendor)
                    .setName(name)
                    .setManufacture(ManufactureType.valueOf(manufacture.toUpperCase()))
                    .setType(ProductType.valueOf(type.toUpperCase()))
                    .setDescription(description)
                    .setImage(inputStream)
                    .setPrice(price)
                    .setNumberInStock(numberInStock)
                    .build();
            ProductService service = ProductService.getInstance();
            service.insert(product);
            router = new Router(GO_TO_MANAGER_PAGE, REDIRECT);
        } catch (ServletException | IOException | ServiceException e) {
            //log
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}

