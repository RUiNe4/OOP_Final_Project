package com.ProductManagement;

import com.DatabaseFunction.DBConnection;
import com.DatabaseFunction.QueryCart;
import com.DatabaseFunction.QueryProduct;

import java.sql.*;
import java.util.ArrayList;

public class Cart extends QueryCart {
  private int productID;
  private String productName;
  private float productPrice;
  private int productQty;
  private int totalPrice;
  private int cartID;
  private final ArrayList<Cart> cartProducts = new ArrayList<>();
  private DBConnection con = new DBConnection();
  private Statement statement;
  private PreparedStatement st;
  private Connection connection;
  Cart cart;

  public Cart() throws Exception {
    super("jdbc:mysql://localhost:3306/possys", "root", "");
    this.connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
    this.statement = connection.createStatement();
  }

  // setter
  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setProductPrice(float productPrice) {
    this.productPrice = productPrice;
  }

  public void setProductQty(int productQty) {
    this.productQty = productQty;
  }

  public void setCartID(int cartID) {
    this.cartID = cartID;
  }

  public void setProductID(int productID) {this.productID = productID;}
// getter
  public int getProductID() {return productID;}
  public String getProductName() {
    return productName;
  }

  public float getProductPrice() {
    return productPrice;
  }

  public int getProductQty() {
    return productQty;
  }

  public int getCartID() {
    return cartID;
  }

  public ArrayList<Cart> readCartDB() throws Exception {
    Cart cart;
    ResultSet resultSet = statement.executeQuery("select * from cartproducts");
    while (resultSet.next()) {
      cart = new Cart();
      cart.setCartID(resultSet.getInt("cartID"));
      cart.setProductName(resultSet.getString("productName"));
      cart.setProductPrice(resultSet.getFloat("productPrice"));
      cart.setProductQty(resultSet.getInt("productQty"));
      cartProducts.add(cart);
    }
    return cartProducts;
  }

  public int getID() throws Exception {
    int tmpID = readCartDB().get((readCartDB().size() - 1)).getCartID();
    return tmpID + 1;
  }

//  public void saveToDb(ArrayList<Cart> cart) throws SQLException {
//    String insertStm = "insert into cartProducts (cartID, productName, productPrice, productQty) values (?, ?, ?, ?)";
//    this.st = connection.prepareStatement(insertStm);
//    for (int i = 0; i < cart.size(); i++) {
//      st.setInt(1, cart.get(i).getCartID());
//      st.setString(2, cart.get(i).getProductName());
//      st.setFloat(3, cart.get(i).getProductPrice());
//      st.setInt(4, cart.get(i).getProductQty());
//      st.executeUpdate();
//    }
//  }

  public void addToCart(int productID, String productName, float productPrice, int productQty) {
    setProductID(productID);
    setProductName(productName);
    setProductPrice(productPrice);
    setProductQty(productQty);
  }

  public void displayCartProducts() {
    System.out.println(cartProducts.size());
    for (int i = 0; i < cartProducts.size(); i++) {
      System.out.println("Product ID: " + cartProducts.get(i).productID);
      System.out.println("Product name: " + cartProducts.get(i).productName);
      System.out.println("Product Price: " + cartProducts.get(i).productPrice);
      System.out.println("Product Quantity: " + cartProducts.get(i).productQty);
      System.out.println();
    }
  }

  public void displayItem() {
    System.out.println("Product ID: " + productID);
    System.out.println("Product Name: " + productName);
    System.out.println("Product Price: " + productPrice);
    System.out.println("Product Qty: " + productQty);
  }

  public void displayCartByID(int cartID) {
    try {
      displayCart(cartID);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public Cart searchProduct(int pid) {
    try {
      return searchFromProduct(pid);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
  public void updateCartItem(String productName, int productQty) {
    try {
      updateCartProduct(productName, productQty);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}


//  select cartid, group_concat(productName separator ', ') as "Product Name", GROUP_concat(productPrice separator ', ') as "ProductPrice", Group_concat(productQty separator ', ') as "Product Qty" from cartproducts group by cartId;
