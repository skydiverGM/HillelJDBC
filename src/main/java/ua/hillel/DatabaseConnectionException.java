package ua.hillel;

public class DatabaseConnectionException extends RuntimeException {
  public DatabaseConnectionException(String message) {
    super(message);
  }
}
