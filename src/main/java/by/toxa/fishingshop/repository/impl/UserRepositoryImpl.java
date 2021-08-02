package by.toxa.fishingshop.repository.impl;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.entity.Role;
import by.toxa.fishingshop.entity.Status;
import by.toxa.fishingshop.entity.User;
import by.toxa.fishingshop.exception.RepositoryException;
import by.toxa.fishingshop.repository.Repository;
import by.toxa.fishingshop.repository.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.toxa.fishingshop.repository.ColumnName.*;

public class UserRepositoryImpl implements Repository<User> {
    private static final String ADD_USER_QUERY = "INSERT INTO users (login, email, password, name, last_name, phone, role_id, status_id)" +
            " VALUES (?,?,?,?,?,?,(SELECT id FROM roles WHERE role = ?),(SELECT id FROM status WHERE status = ?));";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET login=? ,email=?,password=?,name=?,last_name=?,phone=?, role_id = (SELECT id FROM roles WHERE role = ?),status_id = (SELECT id FROM status WHERE status = ?)\n" +
            "WHERE id=?;";
    private static UserRepositoryImpl instance;

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void insert(User user) throws RepositoryException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_QUERY);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getRole().getValue());
            preparedStatement.setString(8, user.getStatus().getValue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(User user) throws RepositoryException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getRole().getValue());
            preparedStatement.setString(8, user.getStatus().getValue());
            preparedStatement.setInt(9, Math.toIntExact(user.getId()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<User> query(Specification specification) throws RepositoryException {
        List<User> userList = new ArrayList<>();
        PreparedStatement statement = (PreparedStatement) specification.getStatement();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User.UserBuilder()
                        .setId(resultSet.getInt(USERS_ID))
                        .setLogin(resultSet.getString(USERS_LOGIN))
                        .setEmail(resultSet.getString(USERS_EMAIL))
                        .setPassword(resultSet.getString(USERS_PASSWORD))
                        .setName(resultSet.getString(USERS_NAME))
                        .setLastName(resultSet.getString(USERS_LAST_NAME))
                        .setPhone(resultSet.getString(USERS_PHONE))
                        .setRole(Role.valueOf(resultSet.getString(ROLES_ROLE).toUpperCase()))
                        .setStatus(Status.valueOf(resultSet.getString(STATUS_STATUS).toUpperCase()))
                        .build();
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);

        }
        return userList;
    }
}
