module com.MainApplication {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;

  exports com.MainApplication.Controller;
  opens com.MainApplication.Controller to javafx.fxml;
}