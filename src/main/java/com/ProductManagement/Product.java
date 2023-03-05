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
    Connection connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
    this.statement = connection.createStatement();
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

  public ArrayList<Product> readFromDB() throws Exception {
    ResultSet resultSet = statement.executeQuery("select * from products");
    while (resultSet.next()) {
      product = new Product();
      product.setPid(resultSet.getInt("pId"));
      product.setpName(resultSet.getString("pName"));
      product.setpPrice(resultSet.getDouble("pPrice"));
      product.setpQty(resultSet.getInt("pQty"));
      products.add(product);
    }
    return products;
  }

  public void displayProduct() {
    System.out.println("Product ID: " + pid);
    System.out.println("Product name: " + pName);
    System.out.println("Product Price: " + pPrice);
    System.out.println("Product Quantity: " + pQty);
  }

  public void displayProducts() throws Exception {
    for (int i = 0; i < products.size(); i++) {
      System.out.println("Product name: " + products.get(i).pName);
      System.out.println("Product Price: " + products.get(i).pPrice);
      System.out.println("Product Quantity: " + products.get(i).pQty);
      System.out.println();
    }
  }

  public void setProduct(String pName, double pPrice, int pQty) throws Exception {
//    Product product = new Product();
    setpName(pName);
    setpPrice(pPrice);
    setpQty(pQty);
//    return product;
  }

  public void addProduct(String pName, double pPrice, int pQty) throws Exception {
    setProduct(pName, pPrice, pQty);
    addQuery(pName, pPrice, pQty);
    products.add(product);
  }

  public void deleteProduct(int pid) throws Exception {
    deleteItem(pid);
  }

  public void updateProduct(int pid, String pName) throws Exception {
    updateQuery(pid, pName);
  }

  public Product searchProduct(int pid) throws Exception {
    return searchItem(pid);
  }
}
