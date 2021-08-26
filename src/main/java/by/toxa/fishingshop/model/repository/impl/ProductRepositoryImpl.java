package by.toxa.fishingshop.model.repository.impl;

import by.toxa.fishingshop.model.connection.CustomConnectionPool;
import by.toxa.fishingshop.model.entity.ManufactureType;
import by.toxa.fishingshop.model.entity.Product;
import by.toxa.fishingshop.model.entity.ProductType;
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

public class ProductRepositoryImpl implements Repository<Product> {
    private static final Logger logger = LogManager.getLogger();
    private static final String ADD_PRODUCT_QUERY = "INSERT INTO products (vendor,name, manufacture_id, type_id, description, image, price, number_in_stock)" +
            " VALUES (?,?,(SELECT id FROM product_manufacture WHERE manufacture = ?),(SELECT id FROM product_types WHERE type = ?),?,?,?,?);";
    private static final String UPDATE_PRODUCT_QUERY = "UPDATE products SET vendor=?, name=? ,manufacture_id=(SELECT id FROM product_manufacture WHERE manufacture = ?)," +
            "type_id=(SELECT id FROM product_types WHERE type = ?),description=?,price=?, number_in_stock = ? WHERE id=?;";
    private static ProductRepositoryImpl instance;

    private ProductRepositoryImpl() {
    }

    public static ProductRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ProductRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void insert(Product product) throws RepositoryException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_QUERY);
            statement.setString(1, product.getVendor());
            statement.setString(2, product.getName());
            statement.setString(3, product.getManufacture().getValue());
            statement.setString(4, product.getProductType().getType());
            statement.setString(5, product.getDescription());
            statement.setBinaryStream(6, product.getImage());
            statement.setBigDecimal(7, product.getPrice());
            statement.setInt(8, product.getNumberInStock());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not create query. Exception: ", e);
            throw new RepositoryException("Can not create query. Exception:", e);
        }
    }

    @Override
    public void update(Product product) throws RepositoryException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_QUERY);
            statement.setString(1, product.getVendor());
            statement.setString(2, product.getName());
            statement.setString(3, product.getManufacture().getValue());
            statement.setString(4, product.getProductType().getType());
            statement.setString(5, product.getDescription());
            statement.setBigDecimal(6, product.getPrice());
            statement.setInt(7, product.getNumberInStock());
            statement.setInt(8, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can not create query. Exception: ", e);
            throw new RepositoryException("Can not create query. Exception: ", e);
        }
    }

    @Override
    public List<Product> query(Specification specification) throws RepositoryException {
        List<Product> productList = new ArrayList<>();
        PreparedStatement statement = (PreparedStatement) specification.getStatement();
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
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

                productList.add(product);
            }

        } catch (SQLException e) {
            logger.error("Can not create query. Exception: ", e);
            throw new RepositoryException("Can not create query. Exception: ", e);
        }
        return productList;
    }
}
