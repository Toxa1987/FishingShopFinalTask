package by.toxa.fishingshop.repository;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.entity.Role;
import by.toxa.fishingshop.entity.Status;
import by.toxa.fishingshop.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String ADD_USER_QUERY = "INSERT INTO users (login, email, password, name, last_name, phone, role_id, status_id)" +
            " VALUES (?,?,?,?,?,?,(SELECT id FROM roles WHERE role = ?),(SELECT id FROM status WHERE status = ?));";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET login=? ,email=?,password=?,name=?,last_name=?,phone=?, role_id = (SELECT id FROM roles WHERE role = ?),status_id = (SELECT id FROM status WHERE status = ?)\n" +
            "WHERE id=?;";


    public void insertUser(User user) {
        Connection connection = CustomConnectionPool.getInstance().getConnection();
        try {
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
            //log
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                //log
            }
        }
    }

    public void updateUser(User user) {
        Connection connection = CustomConnectionPool.getInstance().getConnection();
        try {
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
            //log
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                //log
            }
        }
    }

    public List<User> query(Specification specification) {
        List<User> userList = new ArrayList<>();
        PreparedStatement statement = (PreparedStatement) specification.getStatement();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User.UserBuilder()
                        .setId(resultSet.getInt(1))
                        .setLogin(resultSet.getString(2))
                        .setEmail(resultSet.getString(3))
                        .setPassword(resultSet.getString(4))
                        .setName(resultSet.getString(5))
                        .setLastName(resultSet.getString(6))
                        .setPhone(resultSet.getString(7))
                        .setRole(Role.valueOf(resultSet.getString(8).toUpperCase()))
                        .setStatus(Status.valueOf(resultSet.getString(9).toUpperCase()))
                        .build();
                userList.add(user);
            }
           } catch (SQLException e) {
            //log
            //throw new Exception
        }
        return userList;
    }
}
