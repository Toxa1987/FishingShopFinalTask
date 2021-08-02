package by.toxa.fishingshop.repository.impl;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FindAllProductsSpecification implements Specification {
    private static final String GET_ALL = "" +
            "SELECT products.id,vendor,manufacture,type,description,image,price,number_in_stock FROM products\n" +
            "INNER JOIN product_manufacture AS pm ON products.manufacture_id = pm.id\n" +
            "INNER JOIN product_types AS pt ON products.type_id = pt.id";

    public FindAllProductsSpecification() {
    }

    @Override
    public Statement getStatement() {
        PreparedStatement preparedStatement = null;
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            preparedStatement = connection.prepareStatement(GET_ALL);

        } catch (SQLException e) {
            //log
        }
        return preparedStatement;
    }
}
