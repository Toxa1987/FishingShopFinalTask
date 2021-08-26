package by.toxa.fishingshop.model.service;

import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.repository.Specification;

import java.util.List;

/**
 * The interface Service.
 * <p>
 * Include methods which interact with Api repository like insert, update, select.
 *
 * @param <T> the type parameter
 * @author Anton Pysk
 */
public interface Service<T> {
    /**
     * Insert.
     *
     * @param ob the ob
     * @throws ServiceException the service exception
     */
    void insert(T ob) throws ServiceException;

    /**
     * Update.
     *
     * @param ob the ob
     * @throws ServiceException the service exception
     */
    void update(T ob) throws ServiceException;

    /**
     * Find list of ob.
     *
     * @param specification the {@link Specification }
     * @return the list
     * @throws ServiceException the service exception
     */
    List<T> query(Specification specification) throws ServiceException;
}
