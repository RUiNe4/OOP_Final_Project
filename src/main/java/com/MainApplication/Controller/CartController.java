package com.MainApplication.Controller;

import com.ProductManagement.Cart;
import com.ProductManagement.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class CartController {
  private SceneController sceneController = new SceneController();
  @FXML
  private Label prodExDate;

  @FXML
  private Label productName;

  @FXML
  private Label productPrice;

  @FXML
  private Label productQty;

  @FXML
  private Label totalPrice;
  private Cart cart;
  private ProductController productController;
  private ArrayList<Cart> carts;

  public CartController() throws Exception {
  }
  public void deleteButton(ActionEvent event) {
    try {
      sceneController.switchSceneButton(event, "product-view.fxml");
      System.out.println("Product ID ::::  is Deleted");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
  public void increase(MouseEvent event) throws Exception {
    productController = new ProductController();
    carts = productController.getCartProduct();
    System.out.println(cart.getCartID());

//    System.out.println("Increase");
//    carts.get(0).displayItem();
  }
  public void decrease(MouseEvent event){
//    System.out.println("Decrease");
//    carts.get(0).displayItem();

  }
  public void setCart(Cart cart) {
    this.cart = cart;
    productName.setText(cart.getProductName());
    productPrice.setText(String.valueOf(cart.getProductPrice()));
    productQty.setText(String.valueOf(cart.getProductQty()));
  }
}
