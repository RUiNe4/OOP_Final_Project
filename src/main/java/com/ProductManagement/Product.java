package com.ProductManagement;

import com.DatabaseFunction.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import DatabaseFunction.QueryFunction;
import java.sql.*;
import java.util.ArrayList;

public class Product extends QueryFunction {
  private String imgSrc;
  private int pid;
  private String pName;
  private Double pPrice;
  private int pQty;
  private ArrayList<Product> products = new ArrayList<>();
  Product product;
  private final Statement statement;
  DBConnection con = new DBConnection();

  public Product() throws Exception {
    Connection connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
    this.statement = connection.createStatement();
  }

  //getter

  public void setImgSrc(String imgSrc) {
    this.imgSrc = imgSrc;
  }

  public String getImgSrc() {
    return imgSrc;
  }

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

  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }

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

//  public void setpType(String pType) {
//    this.pType = pType;
//  }

  public ArrayList<Product> readFromDB() throws Exception {
    ResultSet resultSet = statement.executeQuery("select * from products");
    while (resultSet.next()) {
      product = new Product();
      product.setpName(resultSet.getString("pName"));
      product.setPid(resultSet.getInt("pId"));
      product.setpPrice(resultSet.getDouble("pPrice"));
      product.setpQty(resultSet.getInt("pQty"));
//      product.setpType(resultSet.getString("pType"));
      product.setImgSrc(resultSet.getString("productImg"));

      products.add(product);
    }
    return products;
  }

  public void displayAProduct() {
    System.out.println("Product name: " + product.pName);
    System.out.println("Product Price: " + product.pPrice);
    System.out.println("Product Quantity: " + product.pQty);
//    System.out.println("Product Type: " + products.get(i).pType);
  }

  public void displayAProduct(int i) throws Exception {
    System.out.println("Product name: " + products.get(i).pName);
    System.out.println("Product Price: " + products.get(i).pPrice);
    System.out.println("Product Quantity: " + products.get(i).pQty);
//    System.out.println("Product Type: " + products.get(i).pType);
  }

  public void displayProduct() throws Exception {
    for (int i = 0; i < products.size(); i++) {
      displayAProduct(i);
      System.out.println();
    }
  }

  public void displayProduct(int pid) throws Exception {
    int i;
    boolean found = false;
    for (i = 0; i < products.size(); i++) {
      if (pid == products.get(i).pid) {
        found = true;
        break;
      }
    }
    if (found)
      displayAProduct(i);
    else
      System.out.println("There's no product with that ID");
  }

  Product setProduct(String pName, double pPrice, int pQty, String productImg) throws Exception {
    product.setpName(pName);
    product.setpPrice(pPrice);
    product.setpQty(pQty);
//    product.setpType(pType);
    product.setImgSrc(productImg);

    return product;
  }

  public void addProduct(String pName, double pPrice, int pQty, String productImg) throws Exception {
    product = new Product();
    product = setProduct(pName, pPrice, pQty, productImg);
    product.addQuery(pName, pPrice, pQty, productImg);
    products.add(product);
  }

  public void deleteProduct(int pid) throws Exception {
    deleteItem(pid);
  }

  public void updateProduct(int pid, String pName) throws Exception {
    updateQuery(pid, pName);
  }
}
