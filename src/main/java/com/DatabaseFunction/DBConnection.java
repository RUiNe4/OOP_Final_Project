package com.DatabaseFunction;
import java.sql.*;
public class DBConnection {
  public Connection getConnection(String url, String user, String password) throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection(url, user, password);
    return connection;
  }

}
  