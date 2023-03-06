package com.MainApplication.Controller;

import com.ProductManagement.Cart;
import com.ProductManagement.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
  private Product product;

  public GridController() throws Exception {

  }

  public void setData(Product product) {
    try{
      this.product = product;
      productName.setText(product.getpName());
      productPrice.setText(String.valueOf(product.getpPrice()));
      productQty.setText(String.valueOf(product.getpQty()));
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  public void addCart(ActionEvent event) {
    try {
      cartProduct = new Cart();
      cartProduct = cartProduct.searchProduct(product.getPid());
      if (cartProduct == null)
        throw new Exception("Null Product");
      cartProduct.addToCart(product.getPid(), cartProduct.getProductName(), cartProduct.getProductPrice(), cartProduct.getProductQty());
      ProductController.getAllProducts(cartProduct);
      sceneController.switchSceneButton(event, "product-view.fxml");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
