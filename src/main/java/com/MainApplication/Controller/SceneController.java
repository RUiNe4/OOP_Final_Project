package com.MainApplication.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class SceneController {
  private Parent root;
  private Scene scene;
  private Stage stage;
  public void switchSceneButton(ActionEvent event, String fxmlName) throws IOException {
    root = FXMLLoader.load(getClass().getResource(fxmlName));
    scene = new Scene(root);
    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }
}
