package com.DatabaseFunction;

import java.sql.*;

public class TemporaryQuery {
  private DBConnection con = new DBConnection();
  private final Statement statement;
  private final Connection connection;
  private PreparedStatement st;
  private ResultSet resultSet;

  public TemporaryQuery(String url, String user, String password) throws Exception {
    this.connection = con.getConnection(url, user, password);
    this.statement = connection.createStatement();
  }

  protected void createTempTable() throws SQLException {
    try {
      String createTable = "create table TempTable(productPrice double)";
      st = connection.prepareStatement(createTable);
      st.executeUpdate();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  protected void addToTemp(double productPrice) throws SQLException {
    String insertStm = "insert into temptable (productPrice) values (?)";
    st = connection.prepareStatement(insertStm);
    st.setDouble(1, productPrice);
    st.executeUpdate();
  }

  protected void dropTempTable() throws SQLException {
    String dropStm = "drop table tempTable";
    st = connection.prepareStatement(dropStm);
    st.executeUpdate();
  }
}
