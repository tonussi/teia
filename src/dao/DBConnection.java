package dao;

import java.sql.Connection;

public interface DBConnection {

	public Connection connectToDatabaseOrDie();
}
