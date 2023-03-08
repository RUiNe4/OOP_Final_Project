package com.MainApplication.Controller;

import com.ProductManagement.Cart;
import com.ProductManagement.Product;
import com.ProductManagement.TempCart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

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
  private TempCart tempCart = new TempCart();
  private ProductController productController;

  public CartController() throws Exception {
  }

  public void removeButton(ActionEvent event) {
    try {
      product = product.searchProduct(cart.getProductID());
      tempCart = tempCart.searchTemp(cart.getProductID());
      tempCart.updateTempCart(cart.getProductID(), product.getPqty());
      cart.setProductQty(0);
      ProductController.removeFromCart(cart);
      sceneController.switchSceneButton(event, "product-view.fxml");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void increase(MouseEvent event) throws Exception {
    tempCart = tempCart.searchTemp(cart.getProductID());
    if(tempCart.getProductQty() == 0){
      tempCart.deleteItem(cart.getProductID());
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("OUT OF STOCK");
      alert.setHeaderText("IMPORTANT");
      alert.setContentText("Product IS OUT OF STOCK");
      alert.showAndWait();
    } else {
      cart.setProductQty(cart.getProductQty()+1);
      tempCart.setProductQty(tempCart.getProductQty()-1);
      productQty.setText(String.valueOf(cart.getProductQty()));
    }
    tempCart.updateTempCart(cart.getProductID(), tempCart.getProductQty());
    sceneController.switchSceneButton(event, "product-view.fxml");
  }

  public void decrease(MouseEvent event) throws Exception {
    tempCart = tempCart.searchTemp(cart.getProductID());
    if(cart.getProductQty() == 1){
      ProductController.removeFromCart(cart);
      tempCart.setProductQty(tempCart.getProductQty()+1);
    } else {
      cart.setProductQty(cart.getProductQty()-1);
      productQty.setText(String.valueOf(cart.getProductQty()+1));
      tempCart.setProductQty(tempCart.getProductQty()+1);
    }

    tempCart.updateTempCart(cart.getProductID(), tempCart.getProductQty());
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
