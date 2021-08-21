package by.toxa.fishingshop.model.repository.impl;

import by.toxa.fishingshop.model.connection.CustomConnectionPool;
import by.toxa.fishingshop.model.entity.Order;
import by.toxa.fishingshop.model.entity.OrderStatus;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.model.repository.Repository;
import by.toxa.fishingshop.model.repository.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.toxa.fishingshop.model.repository.ColumnName.*;

public class OrderRepositoryImpl implements Repository<Order> {
    private static final Logger logger = LogManager.getLogger();
    private static final String UPDATE_ORDER_QUERY = "" +
            "UPDATE orders SET order_status_id = (SELECT id FROM order_status WHERE status = ?)" +
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
        logger.warn("Method insert in order repository unsupported");
        throw new UnsupportedOperationException("Method is not allowed");
    }

    @Override
    public void update(Order ob) throws RepositoryException {
        PreparedStatement preparedStatement = null;
        Connection connection = CustomConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_QUERY);
            preparedStatement.setString(1, ob.getStatus().getValue());
            preparedStatement.setLong(2, ob.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not update order.", e);
            throw new RepositoryException("Can not update order.", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                logger.error("Something wrong while connection closes.", e);
            }
        }
    }

    @Override
    public List<Order> query(Specification specification) throws RepositoryException {
        List<Order> orderList = new ArrayList<>();
        try (PreparedStatement preparedStatement = (PreparedStatement) specification.getStatement()) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order.OrderBuilder()
                        .setId(resultSet.getLong(ORDERS_ID))
                        .setOrderName(resultSet.getString(ORDERS_ORDER))
                        .setDate(resultSet.getDate(ORDERS_DATE).toLocalDate())
                        .setSummaryPrice(resultSet.getBigDecimal(ORDERS_SUMMARY_PRICE))
                        .setOrderStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS).toUpperCase()))
                        .setUserId(resultSet.getLong(ORDERS_USER_ID))
                        .build();
                orderList.add(order);
            }
        } catch (SQLException e) {
            logger.error("Can not create query. Exception: ", e);
            throw new RepositoryException("Can not create query. Exception: ", e);
        }
        return orderList;
    }
}
