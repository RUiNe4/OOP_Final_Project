package com.ProductManagement;

import com.DatabaseFunction.*;
//import DatabaseFunction.QueryFunction;
import java.sql.*;
import java.util.ArrayList;

public class Product extends QueryProduct {
  private int pid;
  private String pName;
  private Double pPrice;
  private int pQty;
  private ArrayList<Product> products = new ArrayList<>();
  private Product product;
  private Statement statement;
  private DBConnection con = new DBConnection();

  public Product() throws Exception {
    super("jdbc:mysql://localhost:3306/possys", "root", "");
    try{
      Connection connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
      this.statement = connection.createStatement();
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  //getter

  public String getpName() {
    return pName;
  }

  public Double getpPrice() {
    return pPrice;
  }

  public int getpQty() {
    return pQty;
  }

  public int getPid() {
    return pid;
  }
  // setter

  public void setPid(int pid) {
    this.pid = pid;
  }

  public void setpName(String pName) {
    this.pName = pName;
  }

  public void setpPrice(Double pPrice) {
    this.pPrice = pPrice;
  }

  public void setpQty(Integer pQty) {
    this.pQty = pQty;
  }

  public ArrayList<Product> readFromDB() {
    try{
      ResultSet resultSet = statement.executeQuery("select * from products");
      while (resultSet.next()) {
        product = new Product();
        product.setPid(resultSet.getInt("pId"));
        product.setpName(resultSet.getString("pName"));
        product.setpPrice(resultSet.getDouble("pPrice"));
        product.setpQty(resultSet.getInt("pQty"));
        products.add(product);
      }
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
    return products;
  }

  public void displayProduct() {
    System.out.println("Product ID: " + pid);
    System.out.println("Product name: " + pName);
    System.out.println("Product Price: " + pPrice);
    System.out.println("Product Quantity: " + pQty);
  }

  public void displayProducts() {
    for (int i = 0; i < products.size(); i++) {
      System.out.println("Product name: " + products.get(i).pName);
      System.out.println("Product Price: " + products.get(i).pPrice);
      System.out.println("Product Quantity: " + products.get(i).pQty);
      System.out.println();
    }
  }

  public void setProduct(String pName, double pPrice, int pQty) {
    setpName(pName);
    setpPrice(pPrice);
    setpQty(pQty);
  }

  public void addProduct(String pName, double pPrice, int pQty) {
    setProduct(pName, pPrice, pQty);
    try {
      addQuery(pName, pPrice, pQty);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    products.add(product);
  }

  public void deleteProduct(int pid) {
    try {
      deleteItem(pid);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void updateProduct(int pid, int pQty) {
    try {
      updateQtyQuery(pid, pQty);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public Product searchProduct(int pid) {
    try {
      return searchItem(pid);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
}
