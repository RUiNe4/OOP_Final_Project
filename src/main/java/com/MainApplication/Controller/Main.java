package com.MainApplication.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
  @Override
  public void start(Stage stage) throws IOException {
//    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("product-view.fxml"));
//    Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
//    scene.getStylesheets().add(getClass().getResource("Style/styles.css").toExternalForm());
//    stage.setTitle("POS System - Group 7");
//    stage.setScene(scene);
//    stage.setResizable(false);
//    stage.show();
    try {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
      Scene scene = new Scene(root, 1366, 768);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) throws Exception {
//    Cart cart = new Cart();
    launch();
  }
}