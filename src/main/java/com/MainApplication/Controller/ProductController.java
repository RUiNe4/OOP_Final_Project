package com.MainApplication.Controller;

import com.ProductManagement.Cart;
import com.ProductManagement.Product;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReferenceArray;

import javafx.scene.control.Label;


public class ProductController extends GridController implements Initializable {
  private static Cart cart;
  @FXML
  private GridPane verticalGrid;
  private final Product product = new Product();
  @FXML
  private GridPane horizonGrid;
  @FXML
  private Label cartStatus;
  private SceneController sceneController = new SceneController();

  public ProductController() throws Exception {
  }

  private void itemDisplay() throws Exception {
    ArrayList<Product> products = product.readFromDB();
    int column = 0;
    int row = 1;
    for (int i = 0; i < products.size(); i++) {
      FXMLLoader fxmlLoader = new FXMLLoader(ProductController.class.getResource("item-view.fxml"));
      AnchorPane anchorPane = fxmlLoader.load();
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

  protected static ArrayList<Cart> getAllProducts(Cart product) {
    cart = product;
    carts.add(cart);
    return carts;
  }

  public void cartItem() throws Exception {
    int column = 1;
    if (carts.isEmpty()) {
      cartStatus.setText("Empty Cart");
    } else {
      cartStatus.setText("My Cart");
      for (int i = 0; i < carts.size(); i++) {
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
      }
    }
  }

  public void clearCart(ActionEvent event) throws IOException {
    carts.removeAll(carts);
    sceneController.switchSceneButton(event, "product-view.fxml");
  }

  public void confirmItem(ActionEvent event) {

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
