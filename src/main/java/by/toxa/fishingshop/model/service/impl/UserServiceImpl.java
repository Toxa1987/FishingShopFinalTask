package by.toxa.fishingshop.model.service.impl;

import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.model.repository.Specification;
import by.toxa.fishingshop.model.repository.impl.UserRepositoryImpl;
import by.toxa.fishingshop.model.repository.impl.specification.FindByLoginSpecification;
import by.toxa.fishingshop.model.service.UserService;
import by.toxa.fishingshop.model.util.PasswordCodec;
import by.toxa.fishingshop.model.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private UserRepositoryImpl repository = UserRepositoryImpl.getInstance();
    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
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
            logger.error("Something happened while connected to db", e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean insert(User user) throws ServiceException {
        boolean flag = false;
        try {
            switch (user.getRole()) {
                case GUEST:
                    if (UserValidator.isValidLogin(user.getLogin()) & UserValidator.isValidPassword(user.getPassword())
                            & UserValidator.isValidEmail(user.getEmail())) {
                        String password = PasswordCodec.getInstance().codeString(user.getPassword(), user.getLogin());
                        user.setPassword(password);
                        repository.insert(user);
                        flag = true;
                    }
                    break;
                case MANAGER:
                    if (UserValidator.isValidLogin(user.getLogin()) & UserValidator.isValidPassword(user.getPassword())
                            & UserValidator.isValidEmail(user.getEmail()) & UserValidator.isValidName(user.getName())
                            & UserValidator.isValidLastName(user.getLastName()) & UserValidator.isValidPhone(user.getPhone())) {
                        String password = PasswordCodec.getInstance().codeString(user.getPassword(), user.getLogin());
                        user.setPassword(password);
                        repository.insert(user);
                        flag = true;
                    }
            }
        } catch (RepositoryException e) {
            logger.error("Something happened while connected to db", e);
            throw new ServiceException(e);
        }
        return flag;
    }

    @Override
    public boolean update(User user) throws ServiceException {
        boolean flag = false;
        try {
            switch (user.getRole()) {
                case USER:
                    if (UserValidator.isValidName(user.getName()) & UserValidator.isValidLastName(user.getLastName())
                            & UserValidator.isValidPhone(user.getPhone())) {
                        repository.update(user);
                        flag = true;
                    }
                    break;
                case MANAGER:
                    repository.update(user);
                    flag = true;
                    break;
            }
        } catch (RepositoryException e) {
            logger.error("Something happened while connected to db", e);
            throw new ServiceException(e);
        }
        return flag;
    }

    @Override
    public List<User> query(Specification specification) throws ServiceException {
        try {
            return repository.query(specification);
        } catch (RepositoryException e) {
            logger.error("Something happened while connected to db", e);
            throw new ServiceException(e);
        }
    }
}
