package com.MainApplication.Controller;

import com.ProductManagement.Cart;
import com.ProductManagement.ManageProduct;
import com.UserManagement.LoginAuthentication;
import com.UserManagement.ManageEmployee;
import com.UserManagement.User;
import com.ProductManagement.Product;
import com.ProductManagement.TempProduct;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController extends GridController implements Initializable {
  private static Cart cart;
  private static boolean read;
  @FXML
  private GridPane verticalGrid;
  private Product product = new Product();
  private TempProduct tempProduct = new TempProduct();
  @FXML
  private GridPane horizonGrid;
  @FXML
  private Label cartStatus;

  ManageEmployee manageEmployee = new ManageEmployee();
  private User user = manageEmployee.getUserByActive();

  private SceneController sceneController = new SceneController();
  private final static ArrayList<Cart> carts = new ArrayList<>();

  public ProductController() throws Exception {
    System.out.println(user.getUserID());
  }


  private static ArrayList<TempProduct> tempProducts = new ArrayList<>();

  private void itemDisplay() throws Exception {
    TempProduct tempProduct = new TempProduct();
    if (!read) {
      ArrayList<Product> products = product.readFromDB();
      tempProduct.createTable();
      //copy from product db to tempcart db
      for (int i = 0; i < products.size(); i++) {
        tempProduct = tempProduct.searchProduct(products.get(i).getPid());
        tempProduct.addTemp(tempProduct.getProductID(), tempProduct.getProductName(), tempProduct.getProductPrice(), tempProduct.getProductQty());
        tempProducts.add(tempProduct);
      }
      read = true;
    } else {
      tempProducts = tempProduct.readFromDB();
    }
    int column = 0;
    int row = 1;

    for (int i = 0; i < tempProducts.size(); i++) {
      FXMLLoader fxmlLoader = new FXMLLoader(ProductController.class.getResource("item-view.fxml"));
      HBox hBox = fxmlLoader.load();
      GridController gridController = fxmlLoader.getController();
      gridController.setData(tempProducts.get(i));
      if (column == 3) {
        column = 0;
        row++;
      }
      verticalGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
      verticalGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
      verticalGrid.setMaxWidth(Region.USE_COMPUTED_SIZE);

      verticalGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
      verticalGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
      verticalGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);

      verticalGrid.add(hBox, column++, row);
      GridPane.setMargin(hBox, new Insets(5));
    }
  }

  protected static ArrayList<Cart> setCartProduct(Cart product) {
    cart = product;
    carts.add(cart);
    return carts;
  }

  public static Cart getCart() {
    return cart;
  }

  public static void removeFromCart(Cart cart) {
    carts.remove(cart);
  }

  public void cartItem() {
    int column = 1;
    if (carts.isEmpty()) {
      cartStatus.setText("Empty Cart");
    } else {
      cartStatus.setText("My Cart");
      for (int i = 0; i < carts.size(); i++) {
        try {
          FXMLLoader fxmlLoader = new FXMLLoader(ProductController.class.getResource("added-item-list.fxml"));
          AnchorPane anchorPane = fxmlLoader.load();
          CartController cartController = fxmlLoader.getController();
          cartController.setCart(carts.get(i));
          horizonGrid.add(anchorPane, column, i);
          GridPane.setMargin(anchorPane, new Insets(5));
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  public void clearCart(ActionEvent event) throws Exception {
    try {
      for (int i = 0; i < carts.size(); i++) {
        tempProduct = tempProduct.searchTemp(carts.get(i).getProductID());
        if (tempProduct == null) {
          tempProduct = new TempProduct();
          tempProduct.addTemp(carts.get(i).getProductID(), carts.get(i).getProductName(), carts.get(i).getProductPrice(), carts.get(i).getProductQty());
        } else {
          tempProduct.updateTempCart(carts.get(i).getProductID(), tempProduct.getProductQty() + carts.get(i).getProductQty());
        }
      }
      carts.removeAll(carts);
      sceneController.switchSceneButton(event, "product-view.fxml");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void confirmItem(ActionEvent event) throws Exception {
    try {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Are you Sure?");
      alert.setHeaderText("Testing");
      alert.setContentText("Print Order?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK) {
          System.out.println("[From product controller]");
        for (int i = 0; i < carts.size(); i++) {
          product = new Product();
          product = product.searchProduct(carts.get(i).getProductID());
          product.updateProduct(carts.get(i).getProductID(), (product.getPqty() - carts.get(i).getProductQty()));
          product.displayProduct();
        }
        cart.saveToDb(carts);
        carts.clear();
        sceneController.switchSceneButton(event, "product-view.fxml");
      } else {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("POS - Group 7");
        alert1.setHeaderText("Back To menu");
        alert1.setContentText("Feel Free to add more Products");
        alert1.showAndWait();
      }
//      System.out.println("[Product Controller]");
//      System.out.println("Cart ID: " + cart.generateID());
//      String filePath = "D:\\OOP_Final_Project\\src\\main\\resources\\com\\MainApplication\\Controller\\invoice\\RCPT" + cart.generateID() + ".pdf";
//
//      Document document = new Document();
//      PdfWriter.getInstance(document, new FileOutputStream(filePath));
//      document.open();
//
//      PdfPTable table = new PdfPTable(3);
//      PdfPCell c1 = new PdfPCell(new Phrase("Product Name"));
//      table.addCell(c1);
//
//      c1 = new PdfPCell(new Phrase("Product Price"));
//      table.addCell(c1);
//
//      c1 = new PdfPCell(new Phrase("Product Quantity"));
//      table.addCell(c1);
//      table.setHeaderRows(1);
//
//      table.addCell(carts.get(i).getProductName());
//      table.addCell(String.valueOf(carts.get(i).getProductPrice()));
//      table.addCell(String.valueOf(carts.get(i).getProductQty()));
//      document.add(table);
//
//
//      document.close();
//      System.out.println("Done");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      itemDisplay();
      cartItem();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
