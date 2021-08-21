package by.toxa.fishingshop.model.service.impl;

import by.toxa.fishingshop.model.entity.Purchase;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.Specification;
import by.toxa.fishingshop.model.repository.impl.PurchaseRepositoryImpl;
import by.toxa.fishingshop.model.service.Service;

import java.util.List;

public class PurchaseService implements Service<Purchase> {
    private static PurchaseService instance;
    private PurchaseRepositoryImpl repository = PurchaseRepositoryImpl.getInstance();

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
        try {
            repository.insert(ob);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Purchase ob) throws ServiceException {

    }

    @Override
    public List<Purchase> query(Specification specification) throws ServiceException {
        try {
            return repository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
