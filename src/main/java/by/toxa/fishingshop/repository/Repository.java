package by.toxa.fishingshop.repository;

import by.toxa.fishingshop.exception.RepositoryException;

import java.util.List;

public interface Repository<T> {
    void insert(T ob) throws RepositoryException;
    void update(T ob) throws RepositoryException;
    List<T> query(Specification specification) throws RepositoryException;
}
