package com.DatabaseFunction;

import com.ProductManagement.TempProduct;

import java.sql.*;

public class TemporaryProductQuery {
  private DBConnection con = new DBConnection();
  private final Statement statement;
  private final Connection connection;
  private PreparedStatement st;
  private ResultSet resultSet;
  public TemporaryProductQuery(String url, String user, String password) throws Exception {
    this.connection = con.getConnection(url, user, password);
    this.statement = connection.createStatement();
  }

  protected void createTempTable() throws SQLException {
    try {
      String createTable = "create table TempTable(" +
        "productID int," +
        "productName varchar(64)," +
        "productPrice double," +
        "productQty int)";
      st = connection.prepareStatement(createTable);
      st.executeUpdate();

      st.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  protected void addToTemp(int productID, String productName, double productPrice, int productQty) throws SQLException {
    String insertStm = "insert into temptable (" +
      "productID, " +
      "productName, " +
      "productPrice, " +
      "productQty) values (?, ?, ?, ?)";
    st = connection.prepareStatement(insertStm);
    st.setInt(1, productID);
    st.setString(2, productName);
    st.setDouble(3, productPrice);
    st.setInt(4, productQty);
    st.executeUpdate();

    st.close();
  }

  protected void dropTempTable() throws SQLException {
    String dropStm = "drop table tempTable";
    st = connection.prepareStatement(dropStm);
    st.executeUpdate();

    st.close();
  }
  protected TempProduct searchFromTemp(int productId) throws Exception{
    String searchStm = "select * from temptable where productID = ?";
    st = connection.prepareStatement(searchStm);
    st.setInt(1, productId);
    resultSet = st.executeQuery();
    if (resultSet.next()) {
      TempProduct item = new TempProduct();
      item.setProductID(resultSet.getInt("productID"));
      item.setProductName(resultSet.getString("productName"));
      item.setProductPrice(resultSet.getFloat("productPrice"));
      item.setProductQty(resultSet.getInt("productQty"));

      resultSet.close();
      st.close();
      return item;
    } else {
      return null;
    }
  }
  protected TempProduct searchFromProduct(int pid) throws Exception {
    String searchStm = "select * from products where pid = ?";
    st = connection.prepareStatement(searchStm);
    st.setInt(1, pid);
    resultSet = st.executeQuery();
    if (resultSet.next()) {
      TempProduct item = new TempProduct();
      item.setProductID(resultSet.getInt("pid"));
      item.setProductName(resultSet.getString("pName"));
      item.setProductPrice(resultSet.getFloat("pPrice"));
      item.setProductQty(resultSet.getInt("pQty"));

      resultSet.close();
      st.close();
      return item;
    } else {
      return null;
    }
  }
  protected void updateQtyQuery(int id, int productQty) throws Exception {
    String updateStm = "update temptable set productQty = ? where productID = ?";
    st = connection.prepareStatement(updateStm);
    st.setInt(1, productQty);
    st.setInt(2, id);
    st.executeUpdate();
    st.close();
  }
  protected void deleteTempItem(int productID) throws Exception{
    String deleteStm = "delete from temptable where productID = ?";
    st = connection.prepareStatement(deleteStm);
    st.setInt(1, productID);
    st.executeUpdate();
    st.close();
  }
}
