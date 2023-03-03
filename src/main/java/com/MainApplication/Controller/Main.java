package com.MainApplication.Controller;

import com.ProductManagement.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("product-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
    scene.getStylesheets().add(getClass().getResource("Style/styles.css").toExternalForm());
    stage.setTitle("POS System - Group 7");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) throws Exception {
    Product product = new Product();
    ArrayList<Product> products;
    products = product.readFromDB();

    product.displayDB();

//    product.updateProduct(1062, "edit");

    launch();
  }
}