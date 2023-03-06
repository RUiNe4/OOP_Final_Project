package com.DatabaseFunction;

import com.ProductManagement.Cart;
import com.ProductManagement.Product;

import java.sql.*;

public class QueryProduct extends DBConnection {
  private DBConnection con = new DBConnection();
  private final Statement statement;
  private final Connection connection;
  private PreparedStatement st;
  private ResultSet resultSet;

  public QueryProduct(String url, String user, String password) throws Exception {
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

  protected void addQuery(String pName, double pPrice, int pQty) throws Exception {
    String insertStm = "insert into products (pName, pPrice, pQty) values (?, ?, ?)";
    st = connection.prepareStatement(insertStm);
    st.setString(1, pName);
    st.setDouble(2, pPrice);
    st.setInt(3, pQty);
    st.executeUpdate();
  }

  protected void updateQtyQuery(int id, int productQty) throws Exception {
    String updateStm = "update products set pQty = ? where pid = ?";
    st = connection.prepareStatement(updateStm);
    st.setInt(1, productQty);
    st.setInt(2, id);
    st.executeUpdate();
  }

  protected Product searchItem(int pid) throws Exception {
    String searchStm = "select * from products where pid = ?";
    st = connection.prepareStatement(searchStm);
    st.setInt(1, pid);
    resultSet = st.executeQuery();
    if (resultSet.next()) {
      Product item = new Product();
      item.setPid(resultSet.getInt("pid"));
      item.setpName(resultSet.getString("pName"));
      item.setpPrice(resultSet.getDouble("pPrice"));
      item.setpQty(resultSet.getInt("pQty"));
      return item;
    } else {
      return null;
    }
  }

  protected void deleteItem(int pid) throws Exception {
    String deleteStm = "delete from products where pid = ?";
    st = connection.prepareStatement(deleteStm);
    st.setInt(1, pid);
    st.executeUpdate();
  }

}
