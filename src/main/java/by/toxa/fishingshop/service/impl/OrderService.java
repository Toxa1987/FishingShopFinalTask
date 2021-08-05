package by.toxa.fishingshop.service.impl;

import by.toxa.fishingshop.entity.Order;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.repository.Specification;
import by.toxa.fishingshop.repository.impl.OrderRepositoryImpl;
import by.toxa.fishingshop.service.Service;

import java.util.List;

public class OrderService implements Service<Order> {
    private OrderRepositoryImpl repository = OrderRepositoryImpl.getInstance();
    private static OrderService instance;

    private OrderService() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    @Override
    public void insert(Order ob) throws ServiceException {
        try {
            repository.insert(ob);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Order ob) throws ServiceException {
        try {
            repository.update(ob);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> query(Specification specification) throws ServiceException {
        return null;
    }
}
