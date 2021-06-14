package by.toxa.fishingshop._main;

import by.toxa.fishingshop.connection.CustomConnectionPool;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Connection connection = CustomConnectionPool.INSTANCE.getConnection();
        try {
            System.out.println(connection.getMetaData().getURL());
            connection.close();
            CustomConnectionPool.INSTANCE.destroyPool();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
