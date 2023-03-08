package com.MainApplication.Controller;

import com.ProductManagement.Cart;
import com.ProductManagement.Product;
import com.ProductManagement.TempCart;
import com.itextpdf.text.Document;
import com.itextpdf.text.Header;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import com.itextpdf.text.pdf.PdfWriter;

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
  private TempCart tempCart = new TempCart();
  @FXML
  private GridPane horizonGrid;
  @FXML
  private Label cartStatus;
  private SceneController sceneController = new SceneController();
  private final static ArrayList<Cart> carts = new ArrayList<>();

  public ProductController() throws Exception {
  }

  private static ArrayList<TempCart> tempCarts = new ArrayList<>();

  private void itemDisplay() throws Exception {
    TempCart tempCart = new TempCart();
    if (!read) {
      ArrayList<Product> products = product.readFromDB();
      tempCart.createTable();
      //copy from product db to tempcart db
      for (int i = 0; i < products.size(); i++) {
        tempCart = tempCart.searchProduct(products.get(i).getPid());
        tempCart.addTemp(tempCart.getProductID(), tempCart.getProductName(), tempCart.getProductPrice(), tempCart.getProductQty());
        tempCarts.add(tempCart);
      }
      read = true;
    } else {
      tempCarts = tempCart.readFromDB();
    }
    int column = 0;
    int row = 1;

    for (int i = 0; i < tempCarts.size(); i++) {
      FXMLLoader fxmlLoader = new FXMLLoader(ProductController.class.getResource("item-view.fxml"));
      AnchorPane anchorPane = fxmlLoader.load();
      GridController gridController = fxmlLoader.getController();
      gridController.setData(tempCarts.get(i));
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

      verticalGrid.add(anchorPane, column++, row);
      GridPane.setMargin(anchorPane, new Insets(10));
    }
  }

  protected static ArrayList<Cart> setCartProduct(Cart product) {
    cart = product;
    carts.add(cart);
    return carts;
  }

  public static void removeFromCart(Cart cart) {
    carts.remove(cart);
  }

  public void cartItem() {
    System.out.println("[Prod C]");
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
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  public void clearCart(ActionEvent event) throws Exception {
    try {
      for (int i = 0; i < carts.size(); i++) {
        tempCart = tempCart.searchTemp(carts.get(i).getProductID());
        if (tempCart == null) {
          tempCart = new TempCart();
          tempCart.addTemp(carts.get(i).getProductID(), carts.get(i).getProductName(), carts.get(i).getProductPrice(), carts.get(i).getProductQty());
        } else {
          tempCart.updateTempCart(carts.get(i).getProductID(), tempCart.getProductQty() + carts.get(i).getProductQty());
        }
      }
      carts.removeAll(carts);
      sceneController.switchSceneButton(event, "product-view.fxml");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void confirmItem(ActionEvent event) throws SQLException {
    try {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Are you Sure?");
      alert.setHeaderText("Testing");
      alert.setContentText("Print Order?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK) {
        for (int i = 0; i < carts.size(); i++) {
          System.out.println("[From prod c]");
          product = product.searchProduct(carts.get(i).getProductID());
          product.displayProduct();
          product.updateProduct(carts.get(i).getProductID(), product.getPqty() - carts.get(i).getProductQty());
          System.out.println("=================================");
          System.out.println("After");
          product.displayProduct();
        }
        cart.saveToDb(carts);
        carts.removeAll(carts);

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
//      String filePath = "D:\\OOP_Final_Project\\src\\main\\resources\\com\\MainApplication\\Controller\\invoice\\" + cart.generateID() + ".pdf";
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
//      for(int i=0;i<carts.size();i++){
//        table.addCell(carts.get(i).getProductName());
//        table.addCell(String.valueOf(carts.get(i).getProductPrice()));
//        table.addCell(String.valueOf(carts.get(i).getProductQty()));
//      }
//
//      document.add(table);
//      document.close();
//
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
