package com.MainApplication.Controller;

import com.ProductManagement.Cart;
import com.ProductManagement.Product;
import com.ProductManagement.TempProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class GridController {
  private final SceneController sceneController = new SceneController();
  private Cart cartProduct;
  @FXML
  private Label productName;
  @FXML
  private Label productQty;
  @FXML
  private Label productPrice;
  private TempProduct tempProduct;
  private Product product;
  public GridController() throws Exception {
  }

  public void setData(TempProduct tempProduct) {
    try {
      this.tempProduct = tempProduct;
      productName.setText(tempProduct.getProductName());
      productPrice.setText(String.valueOf(tempProduct.getProductPrice()));
      productQty.setText(String.valueOf(tempProduct.getProductQty()));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void addCart(ActionEvent event) {
    try {
      product = new Product();
      product = product.searchProduct(tempProduct.getProductID());
      tempProduct = tempProduct.searchTemp(tempProduct.getProductID());
      cartProduct = new Cart();
      cartProduct = cartProduct.searchProduct(tempProduct.getProductID());
      if (cartProduct == null) {
        throw new Exception("Null Product");
      } else if (tempProduct.getProductQty() == 0) {
        tempProduct.deleteItem(tempProduct.getProductID());
        sceneController.switchSceneButton(event, "product-view.fxml");
      } else {
        tempProduct.setProductQty(tempProduct.getProductQty() - 1);
        if (product.getPqty() - tempProduct.getProductQty() == 1) {
          cartProduct.setProductQty(1); // set cart product to 1
          cartProduct.addToCart(
            tempProduct.getProductID(),
            cartProduct.getProductName(),
            cartProduct.getProductPrice(),
            cartProduct.getProductQty()
          );
          ProductController.setCartProduct(cartProduct);
        } else {
          cartProduct = ProductController.getCart();
          cartProduct.setProductQty(ProductController.getCart().getProductQty()+1);
        }
        tempProduct.updateTempCart(
          tempProduct.getProductID(),
          tempProduct.getProductQty()
        );
        sceneController.switchSceneButton(event, "product-view.fxml");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
