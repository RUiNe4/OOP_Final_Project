package com.ProductManagement;

import com.DatabaseFunction.DBConnection;
import com.UserManagement.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ManageProduct extends DBConnection {
  DBConnection con = new DBConnection();
  private final Statement statement;
  private final User user = new User();
  Connection connection;
  private ArrayList<Product> products = new ArrayList<>();
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
        productList.add(product);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return productList;
  }

    public ArrayList<Product> readFromDB() {
      try{
        ResultSet resultSet = statement.executeQuery("select * from products");
        while (resultSet.next()) {
          Product product = new Product();
          product.setPid(resultSet.getInt("pID"));
          product.setPname(resultSet.getString("pName"));
          product.setPprice(resultSet.getDouble("pPrice"));
          product.setPqty(resultSet.getInt("pQty"));
          products.add(product);
        }
      } catch (Exception e){
        System.out.println(e.getMessage());
      }
      return products;
    }
}
