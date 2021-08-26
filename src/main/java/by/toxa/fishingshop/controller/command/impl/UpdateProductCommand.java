package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.Router;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.entity.ManufactureType;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.model.entity.ProductType;
import by.toxa.fishingshop.model.service.impl.ProductServiceImpl;
import by.toxa.fishingshop.model.validator.ProductValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.RequestParameter.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;
import static by.toxa.fishingshop.controller.command.Router.RouterType.REDIRECT;

public class UpdateProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int id = Integer.parseInt(request.getParameter(ID));
        String vendor = request.getParameter(VENDOR);
        String name = request.getParameter(PRODUCT_NAME);
        String manufacture = request.getParameter(PRODUCT_MANUFACTURE);
        String type = request.getParameter(PRODUCT_TYPE);
        String description = request.getParameter(PRODUCT_DESCRIPTION);
        String inputPrice = request.getParameter(PRODUCT_PRICE);
        int numberInStock = Integer.parseInt(request.getParameter(PRODUCT_NUMBER_IN_STOCK));
        if (ProductValidator.isValidPrice(inputPrice)) {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(inputPrice));
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
            ProductServiceImpl service = ProductServiceImpl.getInstance();
            try {
                if (service.update(product)) {
                    router = new Router(GO_TO_MANAGER_PAGE, REDIRECT);
                } else {
                    setRequestAttributes(request, product);
                    router = new Router(UPDATE_PRODUCT_PAGE, FORWARD);
                }
            } catch (ServiceException e) {
                logger.error("Something happened while save new product", e);
                request.setAttribute(EXCEPTION, e);
                router = new Router(ERROR_PAGE, FORWARD);
            }
        } else {
            Product product = new Product.ProductBuilder()
                    .setId(id)
                    .setName(name)
                    .setVendor(vendor)
                    .setManufacture(ManufactureType.valueOf(manufacture.toUpperCase()))
                    .setType(ProductType.valueOf(type.toUpperCase()))
                    .setDescription(description)
                    .setNumberInStock(numberInStock)
                    .build();
            setRequestAttributes(request, product);
            router=new Router(UPDATE_PRODUCT_PAGE,FORWARD);
        }
        return router;
    }

    private void setRequestAttributes(HttpServletRequest request, Product product) {
        if (!ProductValidator.isValidName(product.getName())) {
            request.setAttribute(NAME_EXCEPTION, true);
            product.setName(null);
        }
        if (!ProductValidator.isValidVendor(product.getVendor())) {
            request.setAttribute(VENDOR_EXCEPTION, true);
            product.setVendor(null);
        }
        if (!ProductValidator.isValidNumberInStock(product.getNumberInStock())) {
            request.setAttribute(NUMBER_IN_STOCK_EXCEPTION, true);
            product.setNumberInStock(0);
        }
        if (!ProductValidator.isValidPrice(product.getPrice().toString())) {
            request.setAttribute(PRICE_EXCEPTION, true);
            product.setPrice(BigDecimal.ZERO);
        }
    }
}
