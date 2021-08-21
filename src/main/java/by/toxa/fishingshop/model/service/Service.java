package by.toxa.fishingshop.model.service;

import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.Specification;

import java.util.List;

public interface Service<T> {
    void insert(T ob) throws ServiceException;
    void update(T ob) throws ServiceException;
    List<T> query(Specification specification) throws ServiceException;
}
