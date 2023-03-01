package com.MainApplication.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class LandingController {
  SceneController sceneController = new SceneController();

  public void employeeButton(ActionEvent event) throws IOException {
    sceneController.switchSceneButton(event, "employee-view.fxml");
  }

  public void productButton(ActionEvent event) throws IOException {
    sceneController.switchSceneButton(event, "product-view.fxml");
  }
}