package by.toxa.fishingshop.controller.command.impl;

import by.toxa.fishingshop.controller.command.Command;
import by.toxa.fishingshop.controller.command.RequestParameter;
import by.toxa.fishingshop.controller.command.Router;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindProductByIdSpecification;
import by.toxa.fishingshop.model.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.ERROR_PAGE;
import static by.toxa.fishingshop.controller.command.PagePath.UPDATE_PRODUCT_PAGE;
import static by.toxa.fishingshop.controller.command.RequestAttribute.EXCEPTION;
import static by.toxa.fishingshop.controller.command.RequestAttribute.PRODUCT;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;

public class GetProductToUpdateCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        ProductServiceImpl service = ProductServiceImpl.getInstance();
        try {
            List<Product> productList =  service.query(new FindProductByIdSpecification(id));
            request.setAttribute(PRODUCT,productList.get(0));
            router = new Router(UPDATE_PRODUCT_PAGE, FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
