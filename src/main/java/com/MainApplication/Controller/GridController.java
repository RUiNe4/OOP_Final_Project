package com.MainApplication.Controller;

import com.ProductManagement.Cart;
import com.ProductManagement.Product;
import com.ProductManagement.TempCart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class GridController {
  private final SceneController sceneController = new SceneController();
  private Cart cartProduct;
  @FXML
  private ImageView productImg;
  @FXML
  private Label productName;
  @FXML
  private Label productQty;
  @FXML
  private Label productPrice;
  private TempCart tempCart;
  private Product product;

  public GridController() throws Exception {
  }

  public void setData(TempCart tempCart) {
    try {
      this.tempCart = tempCart;
      productName.setText(tempCart.getProductName());
      productPrice.setText(String.valueOf(tempCart.getProductPrice()));
      productQty.setText(String.valueOf(tempCart.getProductQty()));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void addCart(ActionEvent event) {
    try {
      product = new Product();
      product = product.searchProduct(tempCart.getProductID());
      System.out.println("[Grid Controller]");
      tempCart = tempCart.searchTemp(tempCart.getProductID());

      cartProduct = new Cart();
      cartProduct = cartProduct.searchProduct(tempCart.getProductID());

      if (cartProduct == null) {
        throw new Exception("Null Product");
      }
      cartProduct.setProductQty(1); // set cart product to 1
      cartProduct.addToCart(
        tempCart.getProductID(),
        cartProduct.getProductName(),
        cartProduct.getProductPrice(),
        cartProduct.getProductQty()
      );
      cartProduct.updateCartItem(
        cartProduct.getProductID(),
        cartProduct.getProductQty()
      );
      if (tempCart.getProductQty() == 0) {
        tempCart.deleteItem(tempCart.getProductID());
      } else {
        tempCart.setProductQty(tempCart.getProductQty() - 1);
        if (product.getPqty() - tempCart.getProductQty() == 1) {
          ProductController.setCartProduct(cartProduct);
        } else {
//          cartProduct.setProductQty(cartProduct.getProductQty() + 1);

        }
      }

      tempCart.updateTempCart(
        tempCart.getProductID(),
        tempCart.getProductQty()
      );
      sceneController.switchSceneButton(event, "product-view.fxml");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
