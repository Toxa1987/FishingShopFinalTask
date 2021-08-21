package by.toxa.fishingshop.exception;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(String s) {
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseConnectionException(Throwable cause) {
        super(cause);
    }

    public DatabaseConnectionException() {
    }
}
