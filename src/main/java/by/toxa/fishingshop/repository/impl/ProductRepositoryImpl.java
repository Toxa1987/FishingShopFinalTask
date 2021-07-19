package by.toxa.fishingshop.repository.impl;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.entity.ManufactureType;
import by.toxa.fishingshop.entity.Product;
import by.toxa.fishingshop.entity.ProductType;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.repository.Repository;
import by.toxa.fishingshop.repository.Specification;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.toxa.fishingshop.repository.ColumnName.*;

public class ProductRepositoryImpl implements Repository<Product> {
    private static final String ADD_PRODUCT_QUERY = "INSERT INTO products (vendor, manufacture_id, type_id, description, image, price, number_in_stock)" +
            " VALUES (?,(SELECT id FROM product_manufacture WHERE manufacture = ?),(SELECT id FROM product_types WHERE type = ?),?,?,?,?);";
    private static final String UPDATE_PRODDCT_QUERY = "UPDATE products SET vendor=? ,manufacture_id=(SELECT id FROM product_manufacture WHERE manufacture = ?)," +
            "type_id=(SELECT id FROM product_types WHERE type = ?),description=?,image=?,price=?, number_in_stock = ? WHERE id=?;";
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
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             InputStream imageInputStream = new ByteArrayInputStream(product.getImage())) {
            PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_QUERY);
            statement.setString(1, product.getVendor());
            statement.setString(2, product.getManufacture().getValue());
            statement.setString(3, product.getProductType().getType());
            statement.setString(4, product.getDescription());
            statement.setBinaryStream(5, imageInputStream);
            statement.setBigDecimal(6, product.getPrice());
            statement.setInt(7, product.getNumberInStock());
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(Product product) throws RepositoryException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             InputStream imageInputStream = new ByteArrayInputStream(product.getImage())) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_PRODDCT_QUERY);
            statement.setString(1, product.getVendor());
            statement.setString(2, product.getManufacture().getValue());
            statement.setString(3, product.getProductType().getType());
            statement.setString(4, product.getDescription());
            statement.setBinaryStream(5, imageInputStream);
            statement.setBigDecimal(6, product.getPrice());
            statement.setInt(7, product.getNumberInStock());
            statement.setInt(8, product.getId());
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RepositoryException(e);
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
                        .setManufacture(ManufactureType.valueOf(resultSet.getString(PRODUCT_MANUFACTURE).toUpperCase()))
                        .setType(ProductType.valueOf(resultSet.getString(PRODUCT_TYPES_TYPE).toUpperCase()))
                        .setDescription(resultSet.getString(PRODUCTS_DESCRIPTION))
                        .setImage(resultSet.getBytes(PRODUCTS_IMAGE))
                        .setPrice(resultSet.getBigDecimal(PRODUCTS_PRICE))
                        .setNumberInStock(resultSet.getInt(PRODUCTS_NUMBER_IN_STOCK))
                        .build();
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return productList;
    }
}
