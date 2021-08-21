package by.toxa.fishingshop.model.service.impl;

import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.Specification;
import by.toxa.fishingshop.model.repository.impl.specification.FindByLoginSpecification;
import by.toxa.fishingshop.model.repository.impl.UserRepositoryImpl;
import by.toxa.fishingshop.model.service.AuthenticateService;
import by.toxa.fishingshop.model.service.Service;
import by.toxa.fishingshop.model.util.PasswordCodec;

import java.util.List;
import java.util.Optional;

public class UserService implements Service<User>, AuthenticateService {
    private UserRepositoryImpl repository = UserRepositoryImpl.getInstance();
    private static UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            List<User> userList = repository.query(new FindByLoginSpecification(login));
            if (!userList.isEmpty()) {
                User tempUser = userList.get(0);
                String passwordCode = PasswordCodec.getInstance().codeString(password, login);
                if (passwordCode.equals(tempUser.getPassword())) {
                    user = Optional.of(tempUser);
                }
            }
        } catch (RepositoryException e) {
            //log
            throw new ServiceException(e);
        }
        return user;
    }

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
            repository.update(user);
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
