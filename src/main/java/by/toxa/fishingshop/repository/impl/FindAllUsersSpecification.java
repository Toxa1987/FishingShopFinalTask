package by.toxa.fishingshop.repository.impl;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.entity.Role;
import by.toxa.fishingshop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FindAllUsersSpecification implements Specification {
    private static final String GET_ALL = "" +
            "SELECT users.id,login,email,password,name,last_name,phone,role,status FROM users\n" +
            "INNER JOIN roles AS r ON users.role_id = r.id\n" +
            "INNER JOIN status AS s ON users.status_id = s.id";

    public FindAllUsersSpecification() {
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
