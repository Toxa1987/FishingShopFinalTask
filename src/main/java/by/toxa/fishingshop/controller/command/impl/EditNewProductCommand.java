package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.*;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.service.impl.ProductServiceImpl;
import by.toxa.fishingshop.model.validator.ProductValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigDecimal;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.RequestParameter.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.*;

public class EditNewProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String vendor = request.getParameter(VENDOR);
        String name = request.getParameter(PRODUCT_NAME);
        String manufacture = request.getParameter(PRODUCT_MANUFACTURE);
        String type = request.getParameter(PRODUCT_TYPE);
        String description = request.getParameter(PRODUCT_DESCRIPTION);
        String inputPrice = request.getParameter(PRODUCT_PRICE);
        int numberInStock = Integer.parseInt(request.getParameter(PRODUCT_NUMBER_IN_STOCK));
        if (ProductValidator.isValidPrice(inputPrice)) {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(inputPrice));
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
                ProductServiceImpl service = ProductServiceImpl.getInstance();
                if (service.insert(product)) {
                    router = new Router(GO_TO_MANAGER_PAGE, REDIRECT);
                } else {
                    setRequestAttributes(request, product);
                    router = new Router(EDIT_PRODUCT, FORWARD);
                }
            } catch (ServletException | IOException | ServiceException e) {
                logger.error("Something happened while save new product", e);
                request.setAttribute(EXCEPTION, e);
                router = new Router(ERROR_PAGE, FORWARD);
            }
        } else {
            Product product = new Product.ProductBuilder()
                    .setName(name)
                    .setVendor(vendor)
                    .setManufacture(ManufactureType.valueOf(manufacture.toUpperCase()))
                    .setType(ProductType.valueOf(type.toUpperCase()))
                    .setDescription(description)
                    .setNumberInStock(numberInStock)
                    .build();
            setRequestAttributes(request, product);
            router = new Router(EDIT_PRODUCT, FORWARD);
        }
        return router;
    }

    private void setRequestAttributes(HttpServletRequest request, Product product) {
        if (!ProductValidator.isValidName(product.getName())) {
            request.setAttribute(NAME_EXCEPTION,true);
            product.setName(null);
        }
        if (!ProductValidator.isValidVendor(product.getVendor())) {
            request.setAttribute(VENDOR_EXCEPTION,true);
            product.setVendor(null);
        }
        if (!ProductValidator.isValidNumberInStock(product.getNumberInStock())) {
            request.setAttribute(NUMBER_IN_STOCK_EXCEPTION,true);
            product.setNumberInStock(0);
        }
        if (!ProductValidator.isValidPrice(product.getPrice().toString())) {
            request.setAttribute(PRICE_EXCEPTION,true);
            product.setPrice(BigDecimal.ZERO);
        }
        if (!ProductValidator.isValidImage(product.getImage())){
            request.setAttribute(IMAGE_EXCEPTION,true);
        }
    }
}