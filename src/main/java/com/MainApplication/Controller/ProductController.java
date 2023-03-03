package com.MainApplication.Controller;

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
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class ProductController extends GridController implements Initializable {
  SceneController sceneController = new SceneController();
  @FXML
  private GridPane grid;
  Product product = new Product();
  private Button button;

  public ProductController() throws Exception {
  }
  public Button addButton() {
    button = new Button("ADD");
    button.setId("addButton");
    button.setPrefWidth(110);
    button.setPrefHeight(110);
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
//        Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        double x = addStage.getX();
//        double y = addStage.getY();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-product-view.fxml"));
//        Parent root;
//
//        try {
//          root = loader.load();
//        } catch (IOException e) {
//          throw new RuntimeException(e);
//        }
//        ScaleTransition st = new ScaleTransition(Duration.millis(50), root);
//        st.setInterpolator(Interpolator.EASE_BOTH);
//        st.setFromX(0);
//        st.setFromY(0);
//        st.setToX(1);
//        st.setToY(1);
//
//        Stage stage = new Stage();
//        stage.setTitle("Add");
//        Scene scene = new Scene(root);
//
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
//        stage.setY(y);
//        stage.setX(x);
        try {
          sceneController.switchSceneButton(event, "add-product-view.fxml");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
    return button;
  }
  public void updateGrid(ArrayList<Product> products) throws Exception {
    int column = 0;
    int row = 1;
    for (int i = 0; i < products.size(); i++) {
      FXMLLoader fxmlLoader = new FXMLLoader(ProductController.class.getResource("grid-view.fxml"));
      AnchorPane anchorPane = fxmlLoader.load();
      GridController gridController = fxmlLoader.getController();
      gridController.setData(products.get(i));
      if (column == 3) {
        column = 0;
        row++;
      }
      grid.setMinWidth(Region.USE_COMPUTED_SIZE);
      grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
      grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

      grid.setMinHeight(Region.USE_COMPUTED_SIZE);
      grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
      grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

      grid.add(anchorPane, column++, row);
      GridPane.setMargin(anchorPane, new Insets(10));
    }
    grid.add(addButton(), column++, row);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      ArrayList<Product> products;
      products = product.readFromDB();
      updateGrid(products);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
