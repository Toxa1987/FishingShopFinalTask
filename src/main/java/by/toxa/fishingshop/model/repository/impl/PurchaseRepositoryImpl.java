package by.toxa.fishingshop.model.repository.impl;

import by.toxa.fishingshop.model.connection.CustomConnectionPool;
import by.toxa.fishingshop.model.entity.*;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.model.repository.Repository;
import by.toxa.fishingshop.model.repository.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.toxa.fishingshop.model.entity.OrderStatus.IN_PROCESS;
import static by.toxa.fishingshop.model.repository.ColumnName.*;

public class PurchaseRepositoryImpl implements Repository<Purchase> {
    private static final Logger logger = LogManager.getLogger();

    private static final String ADD_PURCHASE_QUERY = "" +
            "INSERT INTO purchases (order_id,product_id)" +
            " VALUES (?,?);";

    private static final String ADD_ORDER_QUERY = "" +
            "INSERT INTO orders (orders.order, date, order_status_id,summary_price,user_id)" +
            " VALUES (?,?,(SELECT id FROM order_status WHERE status = ?),?,?);";

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
        PreparedStatement insertOrderStatement = null;
        PreparedStatement insertPurchaseStatement = null;
        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("Savepoint");
            Order order = setOrderData(ob.getUserId(), ob.getCart().getSummaryPrice());
            insertOrderStatement = connection.prepareStatement(ADD_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            insertOrderStatement.setString(1, order.getOrderName());
            insertOrderStatement.setDate(2, Date.valueOf(order.getDate()));
            insertOrderStatement.setString(3, order.getStatus().getValue());
            insertOrderStatement.setBigDecimal(4, order.getSummaryPrice());
            insertOrderStatement.setLong(5, order.getUserId());
            insertOrderStatement.executeUpdate();
            ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys();
            long orderId;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getLong(1);
                order.setId(orderId);
            } else {
                logger.error("Can not save new order into db.");
                throw new SQLException("Can not save new order into db.");
            }
            insertPurchaseStatement = connection.prepareStatement(ADD_PURCHASE_QUERY);
            Cart cart = ob.getCart();
            for (Product product : cart.getProducts()
                    ) {
                insertPurchaseStatement.setLong(1, orderId);
                insertPurchaseStatement.setInt(2, product.getId());
                insertPurchaseStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                logger.error("Error while purchase inserts into db", e);
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                logger.error("Rollback db with exception: ", e1);
                throw new RepositoryException("Rollback db with exception: ", e1);
            }
            throw new RepositoryException("Insert purchase into db with exception: " + e, e);
        } finally {
            try {
                if (insertOrderStatement != null) {
                    insertOrderStatement.close();
                }
                if (insertPurchaseStatement != null) {
                    insertPurchaseStatement.close();
                }
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                logger.error("Something wrong while connection closes.", e);
            }
        }
    }

    @Override
    public void update(Purchase ob) throws RepositoryException {
        logger.warn("Method update in purchase repository unsupported");
        throw new UnsupportedOperationException("Method is not allowed");
    }

    @Override
    public List<Purchase> query(Specification specification) throws RepositoryException {
        List<Purchase> list = new ArrayList<>();
        Cart cart = new Cart();
        try (PreparedStatement preparedStatement = (PreparedStatement) specification.getStatement()) {
            ResultSet resultSet = preparedStatement.executeQuery();
            long orderId = 0;
            int i = 1;
            while (resultSet.next()) {
                if (i == 1) {
                    orderId = resultSet.getLong(PURCHASES_ORDER_ID);
                }
                Product product = new Product.ProductBuilder()
                        .setId(resultSet.getInt(PRODUCTS_ID))
                        .setVendor(resultSet.getString(PRODUCTS_VENDOR))
                        .setName(resultSet.getString(PRODUCTS_NAME))
                        .setManufacture(ManufactureType.valueOf(resultSet.getString(PRODUCT_MANUFACTURE).toUpperCase()))
                        .setType(ProductType.valueOf(resultSet.getString(PRODUCT_TYPES_TYPE).toUpperCase()))
                        .setImage(resultSet.getBinaryStream(PRODUCTS_IMAGE))
                        .setDescription(resultSet.getString(PRODUCTS_DESCRIPTION))
                        .setPrice(resultSet.getBigDecimal(PRODUCTS_PRICE))
                        .setNumberInStock(resultSet.getInt(PRODUCTS_NUMBER_IN_STOCK))
                        .build();
                i++;
                cart.addProduct(product);
            }
            Purchase purchase = new Purchase(orderId, cart);
            list.add(purchase);
        } catch (SQLException e) {
            logger.error("Can not create query. Exception: ", e);
            throw new RepositoryException("Can not create query. Exception: ", e);
        }
        return list;
    }

    private Order setOrderData(long userId, BigDecimal summaryPrice) {
        LocalDate date = LocalDate.now();
        String orderName = "FishingShop-" + date + "-" + System.currentTimeMillis();
        Order order = new Order.OrderBuilder()
                .setOrderName(orderName)
                .setDate(date)
                .setOrderStatus(IN_PROCESS)
                .setSummaryPrice(summaryPrice)
                .setUserId(userId)
                .build();
        return order;
    }
}
