package com.MainApplication.Controller;

import com.ProductManagement.Cart;
import com.ProductManagement.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
    try {
      this.product = product;
      productName.setText(product.getPname());
      productPrice.setText(String.valueOf(product.getPprice()));
      productQty.setText(String.valueOf(product.getPqty()));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void addCart(ActionEvent event) {
    try {

      product = product.searchProduct(product.getPid());
      cartProduct = new Cart();
      cartProduct = cartProduct.searchProduct(product.getPid());
      if (cartProduct == null)
        throw new Exception("Null Product");
      cartProduct.setProductQty(1);
//      System.out.println("[Grid Controller]");
//      System.out.println("Seen: " + cartProduct.isSeen());

      product.setPqty(product.getPqty() - 1);
      product.updateProduct(product.getPid(), product.getPqty());
      cartProduct.addToCart(product.getPid(), cartProduct.getProductName(), cartProduct.getProductPrice(), cartProduct.getProductQty());
      cartProduct.updateCartItem(cartProduct.getProductID(), cartProduct.getProductQty());
      ProductController.setCartProduct(cartProduct);

      sceneController.switchSceneButton(event, "product-view.fxml");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
