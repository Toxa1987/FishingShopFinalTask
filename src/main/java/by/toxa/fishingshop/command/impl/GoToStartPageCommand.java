package by.toxa.fishingshop.command.impl;

import by.toxa.fishingshop.command.Command;
import by.toxa.fishingshop.command.PagePath;
import by.toxa.fishingshop.command.RequestAttribute;
import by.toxa.fishingshop.command.Router;
import by.toxa.fishingshop.entity.Product;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.repository.impl.FindAllProductsSpecification;
import by.toxa.fishingshop.service.impl.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.toxa.fishingshop.command.RequestAttribute.PRODUCT_LIST;
import static by.toxa.fishingshop.command.Router.RouterType.*;

public class GoToStartPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
       Router router;
        ProductService service = new ProductService();
        List<Product> productList = null;
        try {
            productList = service.query(new FindAllProductsSpecification());
            request.setAttribute(PRODUCT_LIST,productList);
            router = new Router(PagePath.START_PAGE, FORWARD );
        } catch (ServiceException e) {
            //log
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, REDIRECT);
        }

        return router;
    }
}
