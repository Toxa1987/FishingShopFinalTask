package by.toxa.fishingshop.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public enum CustomConnectionPool {
    INSTANCE;

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> usedConnections;
    private final static int DEFAULT_POOL_SIZE = 32;
    private int poolSize;

    CustomConnectionPool() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("./src/main/resources/database/database.properties"));
            String url = (String) properties.get("url");
            Class.forName((String) properties.get("driver"));
            poolSize = properties.get("poolsize") != null ? Integer.parseInt((String) properties.get("poolsize")) : DEFAULT_POOL_SIZE;
            freeConnections = new ArrayBlockingQueue<>(poolSize);
            usedConnections = new ArrayDeque<>();
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, properties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.offer(proxyConnection);
            }

        } catch (IOException | ClassNotFoundException e) {
            //log
        } catch (SQLException e) {
            //log
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.offer(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            //log
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws SQLException {
        if (connection.getClass() == ProxyConnection.class) {
            usedConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            connection.close();
        }
    }

    public void destroyPool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                freeConnections.take().fullCloseConnection();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (SQLException e) {
                //log;
            }
            deregisterDrivers();
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                //log
            }
        });
    }
}
