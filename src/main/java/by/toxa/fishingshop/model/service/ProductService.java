package by.toxa.fishingshop.model.service;

import by.toxa.fishingshop.exception.ServiceException;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.model.repository.Specification;

import java.util.List;

/**
 * The interface ProductService.
 * <p>
 * Include methods which interact with Api product repository {@link by.toxa.fishingshop.model.repository.impl.ProductRepositoryImpl }
 * like insert, update, select.
 *
 * @author Anton Pysk
 */

public interface ProductService {
    /**
     * Insert.
     *
     * @param ob the product {@link Product}
     * @return boolean true if successful
     * @throws ServiceException the service exception
     */
    boolean insert(Product ob) throws ServiceException;

    /**
     * Update.
     *
     * @param ob the product {@link Product}
     * @return boolean true if successful
     * @throws ServiceException the service exception
     */
    boolean update(Product ob) throws ServiceException;

    /**
     * Find list of products {@link Product}.
     *
     * @param specification the {@link Specification }
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Product> query(Specification specification) throws ServiceException;
}

