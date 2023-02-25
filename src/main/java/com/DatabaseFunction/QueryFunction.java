package com.DatabaseFunction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
    resultSet = statement.executeQuery("select * from product");
    while (resultSet.next()) {
      System.out.println("ID: " + resultSet.getString("pid"));
      System.out.println("Product name: " + resultSet.getString("pName"));
      System.out.println("Product Price: " + resultSet.getFloat("pPrice"));
      System.out.println("Product Quantity: " + resultSet.getInt("pQty"));
      System.out.println("Product Type: " + resultSet.getString("pType"));
      System.out.println();
    }
  }

  public void displayDB(String pid) throws Exception {
    String searchStm = "select * from product where pid = ?";
    st = connection.prepareStatement(searchStm);
    st.setString(1, pid);
    resultSet = st.executeQuery();
    if (resultSet.next()) {
      System.out.println("ID: " + resultSet.getInt("pid"));
      System.out.println("Product name: " + resultSet.getString("pName"));
      System.out.println("Product Price: " + resultSet.getFloat("pPrice"));
      System.out.println("Product Quantity: " + resultSet.getInt("pQty"));
      System.out.println("Product Type: " + resultSet.getString("pType"));
    }
  }
  public void addItem(String pName, String pid, double pPrice, int pQty, String pType) throws Exception {
    String insertStm = "insert into product (pName, pid, pPrice, pQty, pType) values (?, ?, ?, ?, ?)";
    st = connection.prepareStatement(insertStm);
    st.setString(1, pName);
    st.setString(2, pid);
    st.setDouble(3, pPrice);
    st.setInt(4, pQty);
    st.setString(5, pType);
    st.executeUpdate();
  }
  public void updateItemName(String name, String id) throws Exception {
    String updateStm = "update product set pName = ? where pid = ?";
    st = connection.prepareStatement(updateStm);
    st.setString(1, name);
    st.setString(2, id);
    st.executeUpdate();
  }

  public String searchItem(String pid) throws Exception {
    String searchStm = "select * from product where pid = ?";
    st = connection.prepareStatement(searchStm);
    st.setString(1, pid);
    resultSet = st.executeQuery();
    if (resultSet.next()) {
      return pid;
    } else {
      return "No Such Item in DB";
    }
  }

  public void deleleItem(String pid) throws Exception {
    String deleteStm = "delete from product where pid = ?";
    st = connection.prepareStatement(deleteStm);
    st.setString(1, pid);
    if (st.executeUpdate() == 1) {
      displayDB(pid);
    } else {
      System.out.println("No Such item in database");
    }
  }
}
