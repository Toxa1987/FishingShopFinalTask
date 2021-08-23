package by.toxa.fishingshop.model.service.impl;

import by.toxa.fishingshop.model.entity.Purchase;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.Specification;
import by.toxa.fishingshop.model.repository.impl.PurchaseRepositoryImpl;
import by.toxa.fishingshop.model.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PurchaseService implements Service<Purchase> {
    private static final Logger logger = LogManager.getLogger();
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
            logger.error("Something happened while connected to db", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Purchase ob) throws ServiceException {
        logger.error("Method update is not allow in PurchaseService");
        throw new UnsupportedOperationException("Method is not allow");
    }

    @Override
    public List<Purchase> query(Specification specification) throws ServiceException {
        try {
            return repository.query(specification);
        } catch (RepositoryException e) {
            logger.error("Something happened while connected to db", e);
            throw new ServiceException(e);
        }
    }
}
