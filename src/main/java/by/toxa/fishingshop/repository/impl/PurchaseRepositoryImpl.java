package by.toxa.fishingshop.repository.impl;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.entity.Cart;
import by.toxa.fishingshop.entity.Order;
import by.toxa.fishingshop.entity.Product;
import by.toxa.fishingshop.entity.Purchase;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.repository.Repository;
import by.toxa.fishingshop.repository.Specification;

import java.sql.*;
import java.util.List;

public class PurchaseRepositoryImpl implements Repository<Purchase> {

    private static final String ADD_PURCHASE_QUERY = "INSERT INTO purchases (id,user_id,product_id)" +
            " VALUES (?,?,?);";

    private static PurchaseRepositoryImpl instance;

    private PurchaseRepositoryImpl() {
    }

    public static PurchaseRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PurchaseRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void insert(Purchase ob) throws RepositoryException {
        Connection connection = CustomConnectionPool.getInstance().getConnection();
        Savepoint savepoint = null;
        PreparedStatement insertPurchaseStatement = null;
        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("Savepoint");
            long orderId = ob.getOrderId();
            insertPurchaseStatement = connection.prepareStatement(ADD_PURCHASE_QUERY);
            Cart cart = ob.getCart();
            long userId = ob.getUserId();
            for (Product product : cart.getProducts()
                    ) {
                insertPurchaseStatement.setLong(1, orderId);
                insertPurchaseStatement.setLong(2, userId);
                insertPurchaseStatement.setInt(3, product.getId());
                insertPurchaseStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                throw new RepositoryException("Rollback db with exception: ", e);
            }
            throw new RepositoryException("Insert into db with exception: ", e);
        } finally {
            try {
                if (insertPurchaseStatement != null){
                    insertPurchaseStatement.close();
                }
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                //log
            }
        }
    }

    @Override
    public void update(Purchase ob) throws RepositoryException {
        throw new RuntimeException("Method is not allowed");
    }

    @Override
    public List<Purchase> query(Specification specification) throws RepositoryException {
        return null;
    }
}
