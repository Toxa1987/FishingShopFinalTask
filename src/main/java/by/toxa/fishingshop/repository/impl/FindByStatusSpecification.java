package by.toxa.fishingshop.repository.impl;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.entity.Status;
import by.toxa.fishingshop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FindByStatusSpecification implements Specification {
    private static final String GET_BY_STATUS = "" +
            "SELECT users.id,login,email,password,name,last_name,phone,role,status FROM users\n" +
            "INNER JOIN roles AS r ON users.role_id = r.id\n" +
            "INNER JOIN status AS s ON users.status_id = s.id\n" +
            "WHERE status = ?;";
    private String status;

    public FindByStatusSpecification(Status status) {
        this.status = status.getValue();
    }

    @Override
    public Statement getStatement() {
        PreparedStatement preparedStatement = null;
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            preparedStatement = connection.prepareStatement(GET_BY_STATUS);
            preparedStatement.setString(1, status);
        } catch (SQLException e) {
            //log
        }
        return preparedStatement;
    }
}
