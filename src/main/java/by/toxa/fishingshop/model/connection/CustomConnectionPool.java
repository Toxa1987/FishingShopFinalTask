package by.toxa.fishingshop.model.connection;

import by.toxa.fishingshop.exception.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CustomConnectionPool {
    private final static Logger logger = LogManager.getLogger();
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
            logger.warn("Property file not found, used default value of pool size.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Something wrong with current thread" + e);
        } catch (DatabaseConnectionException e) {
            logger.error("can't create connection: ", e);
        }
        if (freeConnections.isEmpty()) {
            logger.fatal("can't create connections, pool is empty");
            throw new RuntimeException("can't create connections, pool is empty");
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
            logger.error("Something wrong with current thread" + e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            usedConnections.remove(connection);
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Something wrong with current thread" + e);
            }
        } else {
            logger.error("Wrong connection is detected");
            throw new RuntimeException("Wrong connection is detected:" + connection.getClass() +
                    "should be ProxyConnection.class ");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                freeConnections.take().fullCloseConnection();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Something wrong with current thread" + e);
            } catch (SQLException e) {
                logger.error("Exception in connection close method" + e);
            }
            deregisterDrivers();
        }
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Exception when deregistering driver " + driver + " " + e);
            }
        });
    }
}
