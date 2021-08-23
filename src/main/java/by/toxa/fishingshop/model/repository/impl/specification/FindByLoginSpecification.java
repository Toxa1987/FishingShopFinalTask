package by.toxa.fishingshop.model.repository.impl.specification;

import by.toxa.fishingshop.model.connection.CustomConnectionPool;
import by.toxa.fishingshop.model.repository.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FindByLoginSpecification implements Specification {
    private static final Logger logger = LogManager.getLogger();
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
            logger.error("Can not create query. Exception: ", e);
        }
        return preparedStatement;
    }
}
