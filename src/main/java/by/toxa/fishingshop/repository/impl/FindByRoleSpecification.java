package by.toxa.fishingshop.repository.impl;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.entity.Role;
import by.toxa.fishingshop.repository.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FindByRoleSpecification implements Specification {
    private static final Logger logger = LogManager.getLogger();
    private static final String GET_BY_ROLE = "" +
            "SELECT users.id,login,email,password,name,last_name,phone,role,status FROM users\n" +
            "INNER JOIN roles AS r ON users.role_id = r.id\n" +
            "INNER JOIN status AS s ON users.status_id = s.id\n" +
            "WHERE role = ?;";
    private String role;

    public FindByRoleSpecification(Role role) {
        this.role = role.getValue();
    }

    @Override
    public Statement getStatement() {
        PreparedStatement preparedStatement = null;
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            preparedStatement = connection.prepareStatement(GET_BY_ROLE);
            preparedStatement.setString(1, role);
        } catch (SQLException e) {
            logger.error("Can not create query. Exception: ", e);
        }
        return preparedStatement;
    }
}
