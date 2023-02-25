package com.ProductManagement;
import com.DatabaseFunction.*;
//import DatabaseFunction.QueryFunction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Product extends QueryFunction {
  private ArrayList<String> productInfo;
  private ArrayList<String> pid;
  private ArrayList<String> pName;
  private ArrayList<Double> pPrice;
  private ArrayList<Integer> pQty;
  private ArrayList<String> pType; //fruit, electronics, clothes...
  DBConnection con = new DBConnection();
  private final Statement statement;

  public Product() throws Exception {
    Connection connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
    this.statement = connection.createStatement();
  }


  //getter

  public ArrayList<String> getPid() throws Exception {
    ResultSet resultSet = statement.executeQuery("select pid from product");
    pid = new ArrayList<>();
    while (resultSet.next()) {
      pid.add(resultSet.getString("pid"));
    }
    return pid;
  }

  public ArrayList<String> getpName() throws Exception {
    ResultSet resultSet = statement.executeQuery("select pName from product");
    pName = new ArrayList<>();
    while (resultSet.next()) {
      pName.add(resultSet.getString("pName"));
    }
    return pName;
  }

  public ArrayList<Double> getpPrice() throws Exception {
    ResultSet resultSet = statement.executeQuery("select pPrice from product");
    pPrice = new ArrayList<>();
    while (resultSet.next()) {
      pPrice.add(resultSet.getDouble("pPrice"));
    }
    return pPrice;
  }
  public ArrayList<Integer> getpQty() throws Exception {
    ResultSet resultSet = statement.executeQuery("select pQty from product");
    pQty = new ArrayList<>();
    while (resultSet.next()) {
      pQty.add(resultSet.getInt("pQty"));
    }
    return pQty;
  }
  public ArrayList<String> getpType() throws Exception {
    ResultSet resultSet = statement.executeQuery("select pType from product");
    pType = new ArrayList<>();
    while (resultSet.next()) {
      pType.add(resultSet.getString("pType"));
    }
    return pType;
  }
}
