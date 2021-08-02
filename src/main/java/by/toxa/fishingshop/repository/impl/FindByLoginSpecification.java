package by.toxa.fishingshop.repository.impl;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FindByLoginSpecification implements Specification {
    private static final String GET_BY_LOGIN_QUERY = "SELECT users.id,login,email,password,name,last_name,phone,role,status FROM users,roles,status\n" +
            "WHERE role_id = roles.id AND status_id = status.id AND login=?";
    private String login;

    public FindByLoginSpecification(String login) {
        this.login = login;
    }

    @Override
    public Statement getStatement() {
        PreparedStatement preparedStatement = null;
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            preparedStatement = connection.prepareStatement(GET_BY_LOGIN_QUERY);
            preparedStatement.setString(1, login);
        } catch (SQLException e) {
            //log
        }
   return preparedStatement;
    }
}
