package com.DatabaseFunction;

import com.ProductManagement.Product;
import org.w3c.dom.ls.LSResourceResolver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
      System.out.println("ID: " + resultSet.getString("pID"));
      System.out.println("Product name: " + resultSet.getString("pName"));
      System.out.println("Product Price: " + resultSet.getFloat("pPrice"));
      System.out.println("Product Quantity: " + resultSet.getInt("pQty"));
      System.out.println();
    }

    statement.close();
    resultSet.close();
  }

  protected void addQuery(String pName, double pPrice, int pQty) throws Exception {
    String insertStm = "insert into products (pName, pPrice, pQty) values (?, ?, ?)";
    st = connection.prepareStatement(insertStm);
    st.setString(1, pName);
    st.setDouble(2, pPrice);
    st.setInt(3, pQty);
    st.executeUpdate();

    st.close();
  }

  protected void updateQtyQuery(int id, int productQty) throws Exception {
    String updateStm = "update products set pQty = ? where pID = ?";
    st = connection.prepareStatement(updateStm);
    st.setInt(1, productQty);
    st.setInt(2, id);
    st.executeUpdate();

    st.close();
  }

  protected Product searchItem(int pid) throws Exception {
    String searchStm = "select * from products where pID = ?";
    st = connection.prepareStatement(searchStm);
    st.setInt(1, pid);
    resultSet = st.executeQuery();
    if (resultSet.next()) {
      Product item = new Product();
      item.setPid(resultSet.getInt("pID"));
      item.setPname(resultSet.getString("pName"));
      item.setPprice(resultSet.getDouble("pPrice"));
      item.setPqty(resultSet.getInt("pQty"));

      st.close();
      resultSet.close();
      return item;
    } else {
      return null;
    }
  }

  protected void deleteItem(int pid) throws Exception {
    String deleteStm = "delete from products where pID = ?";
    st = connection.prepareStatement(deleteStm);
    st.setInt(1, pid);
    st.executeUpdate();

    st.close();
  }

}
