package by.toxa.fishingshop.service.impl;

import by.toxa.fishingshop.entity.Cart;
import by.toxa.fishingshop.entity.Purchase;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.repository.Specification;
import by.toxa.fishingshop.service.Service;

import java.util.List;

public class PurchaseService implements Service<Purchase> {
    private static PurchaseService instance;

    private PurchaseService() {
    }

    public static PurchaseService getInstance() {
        if (instance == null) {
            instance = new PurchaseService();
        }
        return instance;
    }

    @Override
    public void insert(Purchase ob) throws ServiceException {

    }

    @Override
    public void update(Purchase ob) throws ServiceException {

    }

    @Override
    public List<Purchase> query(Specification specification) throws ServiceException {
        return null;
    }
}
