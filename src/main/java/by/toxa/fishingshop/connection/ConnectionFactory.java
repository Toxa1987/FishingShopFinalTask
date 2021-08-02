package by.toxa.fishingshop.connection;

import by.toxa.fishingshop.exception.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final String URL;
    private static final String RESOURCE = "database/database.properties";
    private static final Properties connectionProperties = new Properties();

    static {
        String driverName = null;
        try (InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(RESOURCE)) {
            connectionProperties.load(inputStream);
            driverName = (String) connectionProperties.get("driver");
            Class.forName(driverName);
            URL = (String) connectionProperties.get("url");
        } catch (ClassNotFoundException e) {
            logger.fatal("Can't register driver: " + driverName, e);
            throw new RuntimeException("Can't register driver: " + driverName, e);
        } catch (IOException e) {
            logger.fatal("Can't load properties file: ", e);
            throw new RuntimeException("Can't load properties file: ", e);
        }
    }

    static Connection getConnection() throws DatabaseConnectionException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, connectionProperties);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Unable to establish connection with URL = " + URL);
        }
        return new ProxyConnection(connection);
    }
}
