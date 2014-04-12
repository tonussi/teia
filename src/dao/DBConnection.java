package dao;

import java.sql.Connection;

public interface DBConnection {

  public Connection connect();

  public Connection close();
}
