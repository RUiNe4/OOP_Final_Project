package com.MainApplication.Controller;

import com.ProductManagement.Cart;
import com.ProductManagement.ManageProduct;
import com.UserManagement.LoginAuthentication;
import com.UserManagement.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import com.ProductManagement.Product;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.Label;

public class ProductController extends GridController implements Initializable {
  private static Cart cart;
  @FXML
  private GridPane verticalGrid;
  private Product product = new Product();
  @FXML
  private GridPane horizonGrid;
  @FXML
  private Label cartStatus;

  private SceneController sceneController = new SceneController();
//  private static boolean confirm = false;

  public ProductController() throws Exception {

  }




  private void itemDisplay() {
    ArrayList<Product> products = product.readFromDB();
    int column = 0;
    int row = 1;
    for (int i = 0; i < products.size(); i++) {
      FXMLLoader fxmlLoader = new FXMLLoader(ProductController.class.getResource("item-view.fxml"));
      AnchorPane anchorPane = null;
      try {
        anchorPane = fxmlLoader.load();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
      GridController gridController = fxmlLoader.getController();
      gridController.setData(products.get(i));
      if (column == 3) {
        column = 0;
        row++;
      }
      verticalGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
      verticalGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
      verticalGrid.setMaxWidth(Region.USE_COMPUTED_SIZE);

      verticalGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
      verticalGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
      verticalGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);

      verticalGrid.add(anchorPane, column++, row);
      GridPane.setMargin(anchorPane, new Insets(10));
    }
  }

  private final static ArrayList<Cart> carts = new ArrayList<>();

  protected static ArrayList<Cart> setCartProduct(Cart product) {
    cart = product;
    carts.add(cart);
    return carts;
  }

  public int getCartProductID() throws Exception {
    return cart.getProductID();
  }

  public void cartItem() {
    int column = 1;
    if (carts.isEmpty()) {
      cartStatus.setText("Empty Cart");
    } else {
      cartStatus.setText("My Cart");

      for (int i = 0; i < carts.size(); i++) {
        try {
          FXMLLoader fxmlLoader = new FXMLLoader(ProductController.class.getResource("added-item-list.fxml"));
          AnchorPane anchorPane = fxmlLoader.load();
          CartController cartController = fxmlLoader.getController();

          cartController.setCart(carts.get(i));

          horizonGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
          horizonGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
          horizonGrid.setMaxWidth(Region.USE_COMPUTED_SIZE);

          horizonGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
          horizonGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
          horizonGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);

          horizonGrid.add(anchorPane, column, i);
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  public void clearCart(ActionEvent event) {
    try {
      for(int i=0;i<carts.size();i++){
        product = product.searchProduct(carts.get(i).getProductID());

        product.updateProduct(carts.get(i).getProductID(), product.getPqty()+carts.get(i).getProductQty());
      }
      carts.removeAll(carts);
      sceneController.switchSceneButton(event, "product-view.fxml");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void confirmItem(ActionEvent event) throws SQLException {
    try {
      cart.saveToDb(carts);
      carts.removeAll(carts);
      sceneController.switchSceneButton(event, "product-view.fxml");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      itemDisplay();
      cartItem();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
