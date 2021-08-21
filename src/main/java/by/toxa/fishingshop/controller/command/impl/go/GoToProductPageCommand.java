package by.toxa.fishingshop.controller.command.impl.go;

import by.toxa.fishingshop.controller.command.*;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindProductByIdSpecification;
import by.toxa.fishingshop.model.service.impl.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.toxa.fishingshop.controller.command.PagePath.*;
import static by.toxa.fishingshop.controller.command.PagePath.ERROR_PAGE;
import static by.toxa.fishingshop.controller.command.RequestAttribute.*;
import static by.toxa.fishingshop.controller.command.Router.RouterType.FORWARD;

public class GoToProductPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        ProductService service = ProductService.getInstance();
        try {
            List<Product> productList = service.query(new FindProductByIdSpecification(id));
            request.setAttribute(PRODUCT, productList.get(0));
            router = new Router(PRODUCT_PAGE, FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_PAGE, FORWARD);
        }
        return router;
    }
}
