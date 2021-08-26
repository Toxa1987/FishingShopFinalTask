package by.toxa.fishingshop.model.service;

import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.entity.User;
import by.toxa.fishingshop.model.repository.Specification;

import java.util.List;
import java.util.Optional;

/**
 * The interface UserService.
 * <p>
 * Include methods which interact with Api user repository {@link by.toxa.fishingshop.model.repository.impl.UserRepositoryImpl }
 * like authenticate, insert, update, select.
 *
 * @author Anton Pysk
 */

public interface UserService {
    /**
     * Find authenticated user
     *
     * @param login    {@link String}
     * @param password {@link String}
     * @return Optional {@link Optional} user
     * @throws ServiceException the service exception
     */
    Optional<User> authenticate(String login, String password) throws ServiceException;

    /**
     * Insert.
     *
     * @param ob the user {@link User}
     * @return boolean true if successful
     * @throws ServiceException the service exception
     */
    boolean insert(User ob) throws ServiceException;

    /**
     * update.
     *
     * @param ob the user {@link User}
     * @return boolean true if successful
     * @throws ServiceException the service exception
     */
    boolean update(User ob) throws ServiceException;

    /**
     * Find list of users {@link User}.
     *
     * @param specification the {@link Specification }
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> query(Specification specification) throws ServiceException;

}
