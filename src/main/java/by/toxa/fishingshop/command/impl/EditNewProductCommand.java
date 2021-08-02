package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.Command;
import by.toxa.fishingshop.command.PagePath;
import by.toxa.fishingshop.command.RequestAttribute;
import by.toxa.fishingshop.command.Router;
import by.toxa.fishingshop.entity.ManufactureType;
import by.toxa.fishingshop.entity.Product;
import by.toxa.fishingshop.entity.ProductType;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.service.impl.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigDecimal;

public class EditNewProductCommand implements Command {
    private ProductService service = ProductService.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String vendor = request.getParameter("vendor");
        String manufacture = request.getParameter("manufacture");
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        InputStream inputStream = null;
        try {
            for (Part part : request.getParts()) {
                inputStream = part.getInputStream();
            }

            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
            int numberInStock = Integer.parseInt(request.getParameter("numberInStock"));
            Product product = new Product.ProductBuilder()
                    .setVendor(vendor)
                    .setManufacture(ManufactureType.valueOf(manufacture.toUpperCase()))
                    .setType(ProductType.valueOf(type.toUpperCase()))
                    .setDescription(description)
                    .setImage(inputStream)
                    .setPrice(price)
                    .setNumberInStock(numberInStock)
                    .build();
            service.insert(product);
            router = new Router(PagePath.MANAGER_PAGE, Router.RouterType.FORWARD);
        } catch (ServletException | IOException | ServiceException e) {
            //log
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
        }
        return router;
    }
}

