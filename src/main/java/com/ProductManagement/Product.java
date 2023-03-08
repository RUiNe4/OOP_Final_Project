package com.ProductManagement;

import com.DatabaseFunction.DBConnection;
import com.DatabaseFunction.QueryProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Product extends QueryProduct {
  private int pid;
  private String pname;
  private int pqty;
  private Double pprice;
  private String ptype;

  public String getPtype() {
    return ptype;
  }

  public void setPtype(String ptype) {
    this.ptype = ptype;
  }
  private Product product;
  private Statement statement;
  private DBConnection con = new DBConnection();
  private ArrayList<Product> products = new ArrayList<>();
  private Connection connection;

  public int getPid() {
    return pid;
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  public int getPqty() {
    return pqty;
  }

  public void setPqty(int pqty) {
    this.pqty = pqty;
  }

  public Double getPprice() {
    return pprice;
  }

  public void setPprice(Double pprice) {
    this.pprice = pprice;
  }

  public Product() throws Exception {
    super("jdbc:mysql://localhost:3306/possys", "root", "");
    try{
      Connection connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
      this.statement = connection.createStatement();
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
  public ArrayList<Product> readFromDB() {
    try{
      ResultSet resultSet = statement.executeQuery("select * from products");
      while (resultSet.next()) {
        product = new Product();
        product.setPid(resultSet.getInt("pID"));
        product.setPname(resultSet.getString("pName"));
        product.setPprice(resultSet.getDouble("pPrice"));
        product.setPqty(resultSet.getInt("pQty"));
        product.setPtype(resultSet.getString("pType"));
        products.add(product);
      }
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
    return products;
  }

  public ArrayList<Product> readFromDBbyType(String pType){
    try{
      String sql = "SELECT * FROM products WHERE pType=?";
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, pType);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        product = new Product();
        product.setPid(resultSet.getInt("pID"));
        product.setPname(resultSet.getString("pName"));
        product.setPprice(resultSet.getDouble("pPrice"));
        product.setPqty(resultSet.getInt("pQty"));
        product.setPtype(resultSet.getString("pType"));
        products.add(product);
      }
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
    return products;
  }

  public void displayProduct() {
    System.out.println("Product ID: " + pid);
    System.out.println("Product name: " + pname);
    System.out.println("Product Price: " + pprice);
    System.out.println("Product Quantity: " + pqty);
  }

  public void displayProducts() {
    for (int i = 0; i < products.size(); i++) {
      System.out.println("Product name: " + products.get(i).pname);
      System.out.println("Product Price: " + products.get(i).pprice);
      System.out.println("Product Quantity: " + products.get(i).pqty);
      System.out.println();
    }
  }

  public void setProduct(String pName, double pPrice, int pQty,String pType) {
    setPname(pName);
    setPprice(pPrice);
    setPqty(pQty);
    setPtype(pType);
  }

  public void addProduct(String pName, double pPrice, int pQty,String pType) {
    setProduct(pName, pPrice, pQty,pType);
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
