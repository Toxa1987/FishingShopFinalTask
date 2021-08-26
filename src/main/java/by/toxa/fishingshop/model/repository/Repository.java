package by.toxa.fishingshop.model.repository;

import by.toxa.fishingshop.exception.RepositoryException;

import java.util.List;

/**
 * The interface Repository.
 *
 * Include methods which interact with DB like insert, update, select.
 *
 * @author Anton Pysk
 *
 * @param <T> the type parameter
 */
public interface Repository<T> {
    /**
     * Insert.
     *
     * @param ob the ob
     * @throws RepositoryException the repository exception
     */
    void insert(T ob) throws RepositoryException;

    /**
     * Update.
     *
     * @param ob the ob
     * @throws RepositoryException the repository exception
     */
    void update(T ob) throws RepositoryException;

    /**
     * Query list.
     *
     * @param specification the {@link Specification }
     * @return the list
     * @throws RepositoryException the repository exception
     */
    List<T> query(Specification specification) throws RepositoryException;
}
