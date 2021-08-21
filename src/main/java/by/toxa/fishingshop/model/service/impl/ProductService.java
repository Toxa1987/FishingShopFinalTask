package by.toxa.fishingshop.model.service.impl;

import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.Specification;
import by.toxa.fishingshop.model.repository.impl.ProductRepositoryImpl;
import by.toxa.fishingshop.model.service.Service;

import java.util.List;

public class ProductService implements Service<Product> {
    private ProductRepositoryImpl repository = ProductRepositoryImpl.getInstance();
    private static ProductService instance;

    private ProductService() {
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    @Override
    public void insert(Product product) throws ServiceException {
        try {
            repository.insert(product);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Product product) throws ServiceException {
        try {
            repository.update(product);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> query(Specification specification) throws ServiceException {
        try {
            return repository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
