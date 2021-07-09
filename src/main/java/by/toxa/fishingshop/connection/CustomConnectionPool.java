package by.toxa.fishingshop.connection;

import by.toxa.fishingshop.exception.DatabaseConnectionException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CustomConnectionPool {
    private static CustomConnectionPool instance;
    private static AtomicBoolean isInstanceHas = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock(true);
    private static final String RESOURCE = "database/pool.properties";
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> usedConnections;
    private final static int DEFAULT_POOL_SIZE = 32;
    private int poolSize;

    private CustomConnectionPool() {
        Properties properties = new Properties();
        try (InputStream inputStream = CustomConnectionPool.class.getClassLoader().getResourceAsStream(RESOURCE)) {
            properties.load(inputStream);
            poolSize = properties.get("poolsize") != null ? Integer.parseInt((String) properties.get("poolsize")) : DEFAULT_POOL_SIZE;
            freeConnections = new LinkedBlockingDeque<>(poolSize);
            usedConnections = new LinkedBlockingDeque<>();
            for (int i = 0; i < poolSize; i++) {
                ProxyConnection proxyConnection = (ProxyConnection) ConnectionFactory.getConnection();
                freeConnections.put(proxyConnection);
            }
        } catch (IOException e) {
            //log
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            //log
        } catch (DatabaseConnectionException e) {
            //log;
        }
    }

    public static CustomConnectionPool getInstance() {
        if (!isInstanceHas.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new CustomConnectionPool();
                    isInstanceHas.getAndSet(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            //log
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws SQLException {
        if (connection.getClass() == ProxyConnection.class) {
            usedConnections.remove(connection);
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                //log
            }
        } else {
            connection.close();
            //log.warning
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
