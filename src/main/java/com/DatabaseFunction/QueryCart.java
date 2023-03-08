package com.DatabaseFunction;

import com.ProductManagement.Cart;

import java.sql.*;

public class QueryCart {
  private DBConnection con = new DBConnection();
  private final Statement statement;
  private final Connection connection;
  private PreparedStatement st;
  private ResultSet resultSet;

  public QueryCart(String url, String user, String password) throws Exception {
    this.connection = con.getConnection(url, user, password);
    this.statement = connection.createStatement();
  }

  protected void displayCart(int cartID) throws Exception {
    ResultSet resultSet;
    String searchStm = "select * from cartproducts where cartid = ?";
    st = connection.prepareStatement(searchStm);
    st.setInt(1, cartID);
    resultSet = st.executeQuery();
    while (resultSet.next()) {
      if (resultSet.getInt(cartID) == cartID) {
        System.out.println(resultSet.getInt("cartID"));
        System.out.println(resultSet.getString("productName"));
        System.out.println(resultSet.getFloat("productPrice"));
        System.out.println(resultSet.getInt("productQty"));
      }
    }
    resultSet.close();
    st.close();
  }

  protected void addCartQuery(int productID, String productName, double productPrice, int productQty) throws Exception {
    String insertStm = "insert into cartProducts (productID, productName, productPrice, productQty) values (?, ?, ?, ?)";
    st = connection.prepareStatement(insertStm);
    st.setInt(1, productID);
    st.setString(2, productName);
    st.setDouble(3, productPrice);
    st.setInt(4, productQty);
    st.executeUpdate();
    st.close();
  }

  protected Cart searchFromProduct(int pid) throws Exception {
    String searchStm = "select * from products where pid = ?";
    st = connection.prepareStatement(searchStm);
    st.setInt(1, pid);
    resultSet = st.executeQuery();
    if (resultSet.next()) {
      Cart item = new Cart();
      item.setProductID(resultSet.getInt("pid"));
      item.setProductName(resultSet.getString("pName"));
      item.setProductPrice(resultSet.getFloat("pPrice"));
      st.close();
      resultSet.close();
      connection.close();
      return item;
    } else {
      return null;
    }
  }

  protected void deleteCart(int cartID) throws Exception {
    String deleteStm = "delete from cartproducts where cartID = ?";
    st = connection.prepareStatement(deleteStm);
    st.setInt(1, cartID);
    st.executeUpdate();
    st.close();
  }
  protected void deleteCartProduct(int productID) throws Exception{
    String deleteStm = "delete from cartproducts where productID = ?";
    st = connection.prepareStatement(deleteStm);
    st.setInt(1, productID);
    st.executeUpdate();
    st.close();
  }
  protected void updateCartProduct(int productID, int productQty) throws SQLException {
    try{
      String updateStm = "update cartproducts set productQty = ? where productID = ?";
      st = connection.prepareStatement(updateStm);
      st.setInt(2, productID);
      st.setInt(1, productQty);
      st.executeUpdate();

      st.close();
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
}
