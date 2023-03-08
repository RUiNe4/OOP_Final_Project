package com.MainApplication.Controller;

import com.UserManagement.ManageEmployee;
import com.UserManagement.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    try {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
      Scene scene = new Scene(root, 1366, 768);
      stage.setScene(scene);
      stage.show();
      stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent windowEvent) {
          try {
            ManageEmployee manageEmployee = new ManageEmployee();
            User user = manageEmployee.getUserByActive();
            manageEmployee.editActive(user.getUserID(),0);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
      });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) throws Exception {
    launch();
  }
}