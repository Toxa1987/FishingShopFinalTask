package by.toxa.fishingshop.service.impl;

import by.toxa.fishingshop.entity.User;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.repository.Specification;
import by.toxa.fishingshop.repository.impl.UserRepositoryImpl;
import by.toxa.fishingshop.service.Service;

import java.util.List;

public class UserService implements Service<User> {
    private UserRepositoryImpl repository = UserRepositoryImpl.getInstance();

    @Override
    public void insert(User user) throws ServiceException {
        try {
            repository.insert(user);
        } catch (RepositoryException e) {
            //log
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            repository.insert(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> query(Specification specification) throws ServiceException {
        try {
            return repository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
