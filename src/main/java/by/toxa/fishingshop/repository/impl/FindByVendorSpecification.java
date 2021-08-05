package by.toxa.fishingshop.repository.impl;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.repository.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FindByVendorSpecification implements Specification {
    private static final Logger logger = LogManager.getLogger();
    private static final String GET_PRODUCT = "" +
            "SELECT products.id,vendor,name,type,description,image,price,number_in_stock FROM products\n" +
            "INNER JOIN product_manufacture AS pm ON products.manufacture_id = pm.id\n" +
            "INNER JOIN product_types AS pt ON products.type_id = pt.id\n" +
            "WHERE vendor = ?;";
    private String vendor;

    public FindByVendorSpecification(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public Statement getStatement() {
        PreparedStatement preparedStatement = null;
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            preparedStatement = connection.prepareStatement(GET_PRODUCT);
            preparedStatement.setString(1,vendor);
            } catch (SQLException e) {
            logger.error("Can not create query. Exception: ", e);
        }
        return preparedStatement;
    }
}

