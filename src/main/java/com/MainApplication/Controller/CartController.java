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
  private Label subTotalPrice;
  private Cart cart;
  private Product product = new Product();
  private ProductController productController;

  public CartController() throws Exception {
  }

  public void removeButton(ActionEvent event) {
    try {
      product = product.searchProduct(cart.getProductID());
      product.updateProduct(cart.getProductID(), product.getpQty() +cart.getProductQty());
      cart.setProductQty(0);
      sceneController.switchSceneButton(event, "product-view.fxml");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void increase(MouseEvent event) throws Exception {
//    product = product.searchProduct(cart.getProductID());
    productQty.setText(String.valueOf(cart.getProductQty()+1));
    cart.setProductQty(cart.getProductQty()+1);

    product.setpQty(product.getpQty()-1);
    System.out.println("[From Cart Controller], Product Qty: " + product.getpQty());
//    product.updateProduct(cart.getProductID(), product.getpQty());

    sceneController.switchSceneButton(event, "product-view.fxml");
  }

  public void decrease(MouseEvent event) throws Exception {
    product = product.searchProduct(cart.getProductID());
    productQty.setText(String.valueOf(cart.getProductQty()+1));
    cart.setProductQty(cart.getProductQty()-1);
    product.setpQty(product.getpQty()+1);
    product.updateProduct(cart.getProductID(), product.getpQty());

    sceneController.switchSceneButton(event, "product-view.fxml");
  }

  public void setCart(Cart cart) {
    this.cart = cart;
    subTotalPrice.setText(String.valueOf(cart.getProductPrice()*cart.getProductQty()));
    productName.setText(cart.getProductName());
    productPrice.setText("$" + (cart.getProductPrice()));
    productQty.setText(String.valueOf(cart.getProductQty()));
  }
}
