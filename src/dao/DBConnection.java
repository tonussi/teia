package dao;

import java.sql.Connection;

public interface DBConnection {

  public Connection connect();

  public void close();

  public void verifica();
}
