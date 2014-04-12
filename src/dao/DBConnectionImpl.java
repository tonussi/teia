package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionImpl implements DBConnection {

  Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());

  private final String database, user, pass;

  private Connection connection;

  private Statement statement;

  private ResultSet resultSet;

  public DBConnectionImpl(String database, String user, String pass) {

    this.database = database;
    this.user = user;
    this.pass = pass;

    verifica();

  }

  @Override
  public void verifica() {
    try {
      connection = connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT VERSION()");
      if (resultSet.next())
        System.out.println(resultSet.getString(1));
    } catch (SQLException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
    } finally {
      close();
    }
  }

  @Override
  public Connection connect() {
    Connection connection = null;
    try {
      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://localhost/" + database;
      connection = DriverManager.getConnection(url, user, pass);
    } catch (ClassNotFoundException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
      System.exit(1);
    } catch (SQLException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
      System.exit(2);
    }
    return connection;
  }

  @Override
  public void close() {
    try {
      if (resultSet != null)
        resultSet.close();
      if (statement != null)
        statement.close();
      if (connection != null)
        connection.close();
    } catch (SQLException event) {
      logger.log(Level.WARNING, event.getMessage(), event);
    }
  }
}
