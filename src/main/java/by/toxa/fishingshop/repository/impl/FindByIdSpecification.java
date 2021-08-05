package by.toxa.fishingshop.repository.impl;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.repository.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FindByIdSpecification implements Specification {
    private static final Logger logger = LogManager.getLogger();
    private static final String GET_BY_ID_QUERY = "SELECT users.id,login,email,password,name,last_name,phone,role,status FROM users,roles,status\n" +
            "WHERE role_id = roles.id AND status_id = status.id AND users.id=?";
    private long id;

    public FindByIdSpecification(long id) {
        this.id = id;
    }

    @Override
    public Statement getStatement() {
        PreparedStatement preparedStatement = null;
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            logger.error("Can not create query. Exception: ", e);
        }
        return preparedStatement;
    }
}
