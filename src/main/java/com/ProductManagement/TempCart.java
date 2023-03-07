package com.ProductManagement;

import com.DatabaseFunction.DBConnection;
import com.DatabaseFunction.TemporaryQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TempCart extends TemporaryQuery {
  private DBConnection con = new DBConnection();
  private Statement statement;
  private PreparedStatement st;
  private Connection connection;

  public TempCart() throws Exception {
    super("jdbc:mysql://localhost:3306/possys", "root", "");
    this.connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
    this.statement = connection.createStatement();
  }

  public void createTable() {
    try {
      createTempTable();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void addTemp(float productPrice) {
    try {
      addToTemp(productPrice);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void deleteTempTable() {
    try {
      dropTempTable();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
