package com.ProductManagement;

import com.DatabaseFunction.DBConnection;
import com.DatabaseFunction.TemporaryQuery;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;

import java.sql.*;
import java.util.ArrayList;

public class TempCart extends TemporaryQuery {
  private DBConnection con = new DBConnection();
  private Statement statement;
  private PreparedStatement st;
  private Connection connection;
  private int productID;
  private String productName;
  private int productQty;
  private double productPrice;
  private TempCart tempCart;
  private ArrayList<TempCart> tempCarts = new ArrayList<>();
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

  public TempCart() throws Exception {
    super("jdbc:mysql://localhost:3306/possys", "root", "");
    this.connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
    this.statement = connection.createStatement();
  }
  public ArrayList<TempCart> readFromDB() {
    try{
      ResultSet resultSet = statement.executeQuery("select * from temptable");
      while (resultSet.next()) {
        tempCart = new TempCart();
        tempCart.setProductID(resultSet.getInt("productID"));
        tempCart.setProductName(resultSet.getString("productName"));
        tempCart.setProductPrice(resultSet.getDouble("productPrice"));
        tempCart.setProductQty(resultSet.getInt("productQty"));
        tempCarts.add(tempCart);
      }

      statement.close();
      resultSet.close();
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
    return tempCarts;
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
  public TempCart searchProduct(int pid){
    try {
      return searchFromProduct(pid);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
  public TempCart searchTemp(int productId){
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
