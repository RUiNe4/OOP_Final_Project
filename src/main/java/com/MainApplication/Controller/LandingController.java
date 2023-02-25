package com.MainApplication.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LandingController extends SceneController {
  SceneController sceneController = new SceneController();
    public void employeeButton(ActionEvent event) throws IOException {
      sceneController.switchSceneButton(event, "employee-view.fxml");
    }
    public void productButton(ActionEvent event) throws IOException {
      sceneController.switchSceneButton(event, "product-view.fxml");
    }
}