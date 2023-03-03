package com.MainApplication.Controller;

import com.ProductManagement.Product;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class GridController {
  SceneController sceneController = new SceneController();
  @FXML
  private ImageView productImg;
  @FXML
  private Label productName;
  @FXML
  private TextField prodNameTF;
  @FXML
  private TextField prodQtyTF;
  @FXML
  private TextField prodPriceTF;
  @FXML
  private Button editBtn;

  //  @FXML
//  private TextField prodTypeTF;
  @FXML
  private Label productPrice;
  private Product product;

//  public void uploadImage() throws MalformedURLException {
//    Stage primaryStage = new Stage();
//    final FileChooser f = new FileChooser();
//    productImg = new ImageView();
//    File file = f.showOpenDialog(primaryStage);
//    if (file != null) { // only proceed, if file was chosen
//      Image img = new Image(file.toURI().toURL().toExternalForm());
//      System.out.println(file.toURI().toURL().toExternalForm());
//      productImg.setImage(img);
//    }
//  }

  public void setData(Product product) throws Exception {
    this.product = product;
    productName.setText(product.getpName());
    productPrice.setText(String.valueOf(product.getpPrice()));
  }

  public void deleteButton(ActionEvent event) throws Exception {
    product.deleteProduct(product.getPid());
    sceneController.switchSceneButton(event, "product-view.fxml");
    System.out.println("Product ID :::: " + product.getPid() + " is Deleted");
  }

  public void backButton(ActionEvent event) throws IOException {
    sceneController.switchSceneButton(event, "product-view.fxml");
  }
  public void addButton(ActionEvent event) throws Exception {
    product = new Product();

    product.addProduct(prodNameTF.getText(), (Double.parseDouble(prodPriceTF.getText())), Integer.parseInt(prodQtyTF.getText()));
    sceneController.switchSceneButton(event, "product-view.fxml");

    product.displayProduct();
  }

  public void editButton(ActionEvent e) throws Exception {
//    product.getProduct(product.getPid());

//    product.updateProduct(product.getPid(),prodNameTF.getText());
//    productName.setText(prodNameTF.getText());
//    System.out.println("ID: " + product.getPid());
//    System.out.println("Text: " + prodNameTF.getText());
//    product.displayProduct();
  }

  public void editMenu(ActionEvent event) throws Exception {
    editBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double x = addStage.getX();
        double y = addStage.getY();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit-product-view.fxml"));
        Parent root;
        try {
          root = loader.load();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        ScaleTransition st = new ScaleTransition(Duration.millis(50), root);
        st.setInterpolator(Interpolator.EASE_BOTH);
        st.setFromX(0);
        st.setFromY(0);
        st.setToX(1);
        st.setToY(1);

        Stage stage = new Stage();
        stage.setTitle("Edit");
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setY(y);
        stage.setX(x);
      }
    });
  }
}
