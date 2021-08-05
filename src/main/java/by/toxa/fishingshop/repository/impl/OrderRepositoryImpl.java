package by.toxa.fishingshop.repository.impl;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.entity.Order;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.repository.Repository;
import by.toxa.fishingshop.repository.Specification;

import java.sql.*;
import java.util.List;

public class OrderRepositoryImpl implements Repository<Order> {
    private static final String ADD_ORDER_QUERY = "INSERT INTO orders (order,date,order_status_id)" +
            " VALUES (?,?,(SELECT id FROM order_status WHERE status = ?));";

    private static final String UPDATE_ORDER_QUERY = "UPDATE orders SET order=? ,date=?," +
            "order_status_id = (SELECT id FROM order_status WHERE status = ?)\n" +
            "WHERE id=?;";

    private static OrderRepositoryImpl instance;

    private OrderRepositoryImpl() {
    }

    public static OrderRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new OrderRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void insert(Order ob) throws RepositoryException {
        PreparedStatement insertOrderStatement = null;
        Connection connection = CustomConnectionPool.getInstance().getConnection();
        try {
            insertOrderStatement = connection.prepareStatement(ADD_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            insertOrderStatement.setString(1, ob.getOrderName());
            insertOrderStatement.setObject(2, ob.getDate());
            insertOrderStatement.setString(3, ob.getStatus().getValue());
            insertOrderStatement.executeUpdate();

            ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys();
            long orderId = 0;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getLong(1);
                ob.setId(orderId);
            } else {
                throw new SQLException("Can not make new order.");
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can not add order.", e);
        } finally {
            try {
                if (insertOrderStatement != null) {
                    insertOrderStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                //log
            }
        }
    }

    @Override
    public void update(Order ob) throws RepositoryException {
        PreparedStatement preparedStatement = null;
        Connection connection = CustomConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_QUERY);
            preparedStatement.setString(1, ob.getOrderName());
            preparedStatement.setObject(2, ob.getDate());
            preparedStatement.setString(3, ob.getStatus().getValue());
            preparedStatement.setLong(4, ob.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Can not add order.", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                //log
            }
        }
    }

    @Override
    public List<Order> query(Specification specification) throws RepositoryException {
        return null;
    }
}
