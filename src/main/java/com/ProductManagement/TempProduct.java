package com.ProductManagement;

import com.DatabaseFunction.DBConnection;
import com.DatabaseFunction.TemporaryProductQuery;

import java.sql.*;
import java.util.ArrayList;

public class TempProduct extends TemporaryProductQuery {
  private DBConnection con = new DBConnection();
  private Statement statement;
  private PreparedStatement st;
  private Connection connection;
  private int productID;
  private String productName;
  private int productQty;
  private double productPrice;
  private TempProduct tempProduct;
  private ArrayList<TempProduct> tempProducts = new ArrayList<>();
  public void setProductID(int productID) {
    this.productID = productID;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setProductQty(int productQty) {
    this.productQty = productQty;
  }

  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }

  public int getProductID() {
    return productID;
  }

  public String getProductName() {
    return productName;
  }

  public int getProductQty() {
    return productQty;
  }

  public double getProductPrice() {
    return productPrice;
  }

  public TempProduct() throws Exception {
    super("jdbc:mysql://localhost:3306/possys", "root", "");
    this.connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
    this.statement = connection.createStatement();
  }
  public ArrayList<TempProduct> readFromDB() {
    try{
      ResultSet resultSet = statement.executeQuery("select * from temptable");
      while (resultSet.next()) {
        tempProduct = new TempProduct();
        tempProduct.setProductID(resultSet.getInt("productID"));
        tempProduct.setProductName(resultSet.getString("productName"));
        tempProduct.setProductPrice(resultSet.getDouble("productPrice"));
        tempProduct.setProductQty(resultSet.getInt("productQty"));
        tempProducts.add(tempProduct);
      }

      statement.close();
      resultSet.close();
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
    return tempProducts;
  }

  public void createTable() {
    try {
      createTempTable();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void addTemp(int productID, String productName, double productPrice, int productQty) {
    try {
      addToTemp(productID, productName, productPrice, productQty);
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
  public TempProduct searchProduct(int pid){
    try {
      return searchFromProduct(pid);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
  public TempProduct searchTemp(int productId){
    try {
      return searchFromTemp(productId);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
  public void updateTempCart(int id, int productQty){
    try {
      updateQtyQuery(id, productQty);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  public void display(){
    System.out.println("Product ID: " + productID);
    System.out.println("Product Name: " + productName);
    System.out.println("Product Price: " + productPrice);
    System.out.println("Product Qty: " + productQty);
  }
  public void deleteItem(int productID){
    try {
      deleteTempItem(productID);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
