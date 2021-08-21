package by.toxa.fishingshop.model.service;

import by.toxa.fishingshop.model.entity.Cart;
import by.toxa.fishingshop.exception.ServiceException;

public interface ClearCartService {
    void clearCart(Cart cart) throws ServiceException;
}
