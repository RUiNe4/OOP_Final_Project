package com.DatabaseFunction;
import com.ProductManagement.Product;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.sql.*;

public class QueryFunction extends DBConnection {
  private DBConnection con = new DBConnection();
  private final Statement statement;
  private final Connection connection;
  private PreparedStatement st;
  private ResultSet resultSet;

  public QueryFunction() throws Exception {
    this.connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
    this.statement = connection.createStatement();
  }

  public QueryFunction(String url, String user, String password) throws Exception {
    this.connection = con.getConnection(url, user, password);
    this.statement = connection.createStatement();
  }

  public void displayDB() throws Exception {
    resultSet = statement.executeQuery("select * from products");
    while (resultSet.next()) {
      System.out.println("ID: " + resultSet.getString("pid"));
      System.out.println("Product name: " + resultSet.getString("pName"));
      System.out.println("Product Price: " + resultSet.getFloat("pPrice"));
      System.out.println("Product Quantity: " + resultSet.getInt("pQty"));
      System.out.println();
    }
  }
//
//  public void displayDB(int pid) throws Exception {
//    String searchStm = "select * from product where pid = ?";
//    st = connection.prepareStatement(searchStm);
//    st.setInt(1, pid);
//    resultSet = st.executeQuery();
//    if (resultSet.next()) {
//      System.out.println("ID: " + resultSet.getInt("pid"));
//      System.out.println("Product name: " + resultSet.getString("pName"));
//      System.out.println("Product Price: " + resultSet.getFloat("pPrice"));
//      System.out.println("Product Quantity: " + resultSet.getInt("pQty"));
//      System.out.println("Product Type: " + resultSet.getString("pType"));
//    }
//  }
  protected void addQuery(String pName, double pPrice, int pQty, String imgSrc) throws Exception {
    String insertStm = "insert into products (pName, pPrice, pQty, productImg) values (?, ?, ?, ?)";
    st = connection.prepareStatement(insertStm);
    st.setString(1, pName);
    st.setDouble(2, pPrice);
    st.setInt(3, pQty);
    st.setString(4, imgSrc);
    st.executeUpdate();
  }
  public void updateQuery(int id, String name) throws Exception {
    String updateStm = "update products set pName = ? where pid = ?";
    st = connection.prepareStatement(updateStm);
    st.setString(1, name);
    st.setInt(2, id);
    st.executeUpdate();
  }

  protected int searchItem(int pid) throws Exception {
    String searchStm = "select * from products where pid = ?";
    st = connection.prepareStatement(searchStm);
    st.setInt(1, pid);
    resultSet = st.executeQuery();
    if (resultSet.next()) {
      return pid;
    } else {
      return -1;
    }
  }
  protected int getDBRowSize() throws Exception {
    int row;
    String countStm = "select count(*) from products";
    st = connection.prepareStatement(countStm);
    resultSet = st.executeQuery(countStm);
    resultSet.next();
    row = resultSet.getInt(1);
    return row;
  }
  protected void deleteItem(int pid) throws Exception {
    String deleteStm = "delete from products where pid = ?";
    st = connection.prepareStatement(deleteStm);
    st.setInt(1, pid);
    st.executeUpdate();
  }
}
