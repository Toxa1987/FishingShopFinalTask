package by.toxa.fishingshop.model.service.impl;

import by.toxa.fishingshop.model.entity.Cart;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.impl.specification.FindProductByIdSpecification;
import by.toxa.fishingshop.model.service.ClearCartService;

import java.util.List;

public class ClearCartServiceImpl implements ClearCartService {
    private static ClearCartServiceImpl instance;

    private ClearCartServiceImpl() {
    }

    public static ClearCartServiceImpl getInstance() {
        if (instance == null) {
            instance = new ClearCartServiceImpl();
        }
        return instance;
    }

    @Override
    public void clearCart(Cart cart) throws ServiceException {
        List<Product> productList = cart.getProducts();
        if (!productList.isEmpty()) {
            ProductService service = ProductService.getInstance();
            for (Product pr : productList
                    ) {
                Product product = service.query(new FindProductByIdSpecification(pr.getId())).get(0);
                int numberInStock = product.getNumberInStock();
                product.setNumberInStock(numberInStock + 1);
                service.update(product);
            }
        }
    }
}
