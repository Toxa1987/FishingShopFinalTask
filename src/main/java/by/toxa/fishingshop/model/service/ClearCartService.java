package by.toxa.fishingshop.model.service;

import by.toxa.fishingshop.model.entity.Cart;
import by.toxa.fishingshop.exception.ServiceException;

/**
 * The interface ClearCartService.
 * <p>
 * Include method to clear cart
 *
 * @author Anton Pysk
 */
public interface ClearCartService {
    /**
     *
     * @param cart {@link Cart}
     * @throws ServiceException service exception
     */
    void clearCart(Cart cart) throws ServiceException;
}
