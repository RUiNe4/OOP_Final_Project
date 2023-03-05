package com.MainApplication.Controller;

import com.ProductManagement.Cart;
import com.ProductManagement.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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

  public CartController() throws Exception {
  }
  public void deleteButton(ActionEvent event) throws Exception {
    sceneController.switchSceneButton(event, "product-view.fxml");
    System.out.println("Product ID ::::  is Deleted");
  }
  public void increase(MouseEvent event){
    System.out.println("Increase");
  }
  public void decrease(MouseEvent event){
    System.out.println("Decrease");
  }
  public void setCart(Cart cart) throws Exception {
    this.cart = cart;
    productName.setText(cart.getProductName());
    productPrice.setText(String.valueOf(cart.getProductPrice()));
    productQty.setText(String.valueOf(cart.getProductQty()));
  }
}
