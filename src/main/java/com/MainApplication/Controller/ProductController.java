package com.MainApplication.Controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ProductController {
  SceneController sceneController = new SceneController();
  public void backButton(ActionEvent event) throws IOException {
    sceneController.switchSceneButton(event, "landing-view.fxml");
  }
  public void accessoryButton(ActionEvent event) throws IOException {
    sceneController.switchSceneButton(event, "product-view.fxml");
  }
  public void foodButton(ActionEvent event) throws IOException {
    sceneController.switchSceneButton(event, "product-view.fxml");
  }
  public void clothButton(ActionEvent event) throws IOException {
    sceneController.switchSceneButton(event, "product-view.fxml");
  }
  public void computerButton(ActionEvent event) throws IOException {
    sceneController.switchSceneButton(event, "product-view.fxml");
  }
}
