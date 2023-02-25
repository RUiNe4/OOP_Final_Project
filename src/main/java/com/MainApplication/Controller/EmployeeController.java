package com.MainApplication.Controller;

import javafx.event.ActionEvent;
import java.io.IOException;

public class EmployeeController {
  SceneController sceneController = new SceneController();
  public void backButton(ActionEvent event) throws IOException {
    sceneController.switchSceneButton(event, "landing-view.fxml");
  }
}
