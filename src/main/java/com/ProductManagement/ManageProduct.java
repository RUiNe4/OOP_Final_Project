package com.ProductManagement;

import com.DatabaseFunction.DBConnection;
import com.UserManagement.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ManageProduct extends DBConnection {
  DBConnection con = new DBConnection();
  private final Statement statement;
  private final User user = new User();
  Connection connection;
  public ManageProduct() throws Exception {
    connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
    this.statement = connection.createStatement();
  }

  public ObservableList<Product> fetchProduct(){
    ObservableList <Product> productList = FXCollections.observableArrayList();
    try {
      String qry = "SELECT * FROM products";
      ResultSet rs = statement.executeQuery(qry);
      while (rs.next()){
        Product product = new Product();
        product.setPid(rs.getInt("pID"));
        product.setPname(rs.getString("pName"));
        product.setPprice(rs.getDouble("pPrice"));
        product.setPqty(rs.getInt("pQty"));
        product.setPtype(rs.getString("pType"));
        productList.add(product);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return productList;
  }

  public void insertProduct(String pName,Double pPrice,int pQty,String pType)
  {
    try {
      String qry = "INSERT into products (pName,pPrice,pQty,pType)"
              + "VALUES (?,?,?,?)";
      PreparedStatement psmt = connection.prepareStatement(qry);
      psmt.setString(1,pName);
      psmt.setDouble(2,pPrice);
      psmt.setInt(3,pQty);
      psmt.setString(4,pType);
      psmt.execute();
    }catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  public void deleteProduct(int ID) throws SQLException {
    String sql = "DELETE FROM products WHERE pID=?";

    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(1, ID);

    int rowsDeleted = statement.executeUpdate();
    if (rowsDeleted > 0) {
      System.out.println("A product was deleted successfully!");
    }
  }
  public void editProduct(String pName,int pQty,Double pPrice,
                           String pType,int ID) throws SQLException {
    String sql = "UPDATE products set pName = '" + pName + "', pQty = '"
            + pQty + "',pPrice = '" + pPrice + "',pType = '" + pType + "' WHERE pID = '" + ID + "'";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.execute();
  }
  public Product getProduct(int ID) throws Exception {
    String sql = "SELECT * FROM products WHERE pID=?";
    Product product = new Product();
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(1, ID);
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()){
      product.setPid(resultSet.getInt("pID"));
      product.setPname(resultSet.getString("pName"));
      product.setPprice(resultSet.getDouble("pPrice"));
      product.setPqty(resultSet.getInt("pQty"));
      product.setPtype(resultSet.getString("pType"));
    }
    return product;
  }
  public boolean checkID(int ID) throws SQLException {
    boolean searchFound = false;
    String sql = "SELECT * FROM products WHERE pID=?";

    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(1, ID);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      System.out.println("Found");
      searchFound = true;
    }
    return searchFound;
  }
}
