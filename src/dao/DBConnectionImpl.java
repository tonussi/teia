package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionImpl implements DBConnection {

  Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());

  private String database, user, pass;

  public DBConnectionImpl(String database, String user, String pass) {
    this.database = database;
    this.user = user;
    this.pass = pass;
  }

  @Override
  public Connection connect() {
    Connection conn = null;
    try {
      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://localhost/" + database;
      conn = DriverManager.getConnection(url, user, pass);
    } catch (ClassNotFoundException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
      System.exit(1);
    } catch (SQLException event) {
      logger.log(Level.SEVERE, event.getMessage(), event);
      System.exit(2);
    }
    return conn;
  }

  @Override
  public Connection close() {
    return null;
  }
}
