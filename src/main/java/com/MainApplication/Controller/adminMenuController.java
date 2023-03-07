package com.MainApplication.Controller;

import com.ProductManagement.ManageProduct;
import com.UserManagement.User;
import com.UserManagement.ManageEmployee;
import com.ProductManagement.Product;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminMenuController implements Initializable{
  private Stage stage;
  private Scene scene;
  private Parent root;
  private User user;

  //from age Spinner
  int ageValue;
  ObservableList<User> list;
  ObservableList<Product> plist;

  @FXML
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    SpinnerValueFactory<Integer> valueFactory =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
    valueFactory.setValue(1);
    ageSpinner.setValueFactory(valueFactory);
    //initialize user table
    userName.setCellValueFactory(new PropertyValueFactory<User,String>("userName"));
    firstName.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
    lastName.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
    phone.setCellValueFactory(new PropertyValueFactory<User,String>("phone"));
    gender.setCellValueFactory(new PropertyValueFactory<User,String>("gender"));
    type.setCellValueFactory(new PropertyValueFactory<User,String>("type"));
    password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
    email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
    address.setCellValueFactory(new PropertyValueFactory<User,String>("address"));
    date.setCellValueFactory(new PropertyValueFactory<User,String>("date"));
    age.setCellValueFactory(new PropertyValueFactory<User,Integer>("age"));
    id.setCellValueFactory(new PropertyValueFactory<User,Integer>("userID"));

    pID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("pid"));
    pName.setCellValueFactory(new PropertyValueFactory<Product, String>("pname"));
    pPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("pprice"));
    pQty.setCellValueFactory(new PropertyValueFactory<Product, Integer>("pqty"));

    ManageEmployee manageEmployee = null;
    try {
      manageEmployee = new ManageEmployee();
      list = manageEmployee.fetchUser();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    ManageProduct manageProduct = null;
    try {
      manageProduct = new ManageProduct();
      plist = manageProduct.fetchProduct();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    table.setItems(list);
    pTable.setItems(plist);

    //search Filter for user table
    FilteredList<User> filterUserList = new FilteredList<>(list, b -> true);
//    FilteredList<Product> filteredProductList = new FilteredList<>(plist, b -> true);
    searchFilter.textProperty().addListener((observable, oldValue, newValue) -> {
      filterUserList.setPredicate(user -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }
        String lowercasefilter = newValue.toLowerCase();
        if (user.getUserName().toLowerCase().indexOf(lowercasefilter) != -1) {
          return true;
        } else
          return false;
      });
    });
    table.setItems(filterUserList);
  }
  public void clearTextField(){
    firstnameField.clear();
    lastnameField.clear();
    dateField.clear();
    addressField.clear();
    telephoneField.clear();
    emailField.clear();
    usernameField.clear();
  }

  public void clearUpdateField(){
    firstnameField_Upd.clear();
    lastnameField_Upd.clear();
    dobField_Upd.clear();
    addressField_Upd.clear();
    phoneField_Upd.clear();
    emailField_Upd.clear();
    usernameField_Upd.clear();
    passField_Upd.clear();
    idField.clear();
    typeField_Upd.clear();
    genderField_Upd.clear();
    ageField_Upd.clear();
  }

  int index = -1;

  public void findEmployee(ActionEvent event){
    try {
      User user;
      ManageEmployee manageEmployee = new ManageEmployee();
      if(manageEmployee.checkID(Integer.parseInt(idField.getText()))){
        user = manageEmployee.getUser(Integer.parseInt(idField.getText()));
        firstnameField_Upd.setText(user.getFirstName());
        lastnameField_Upd.setText(user.getLastName());
        ageField_Upd.setText(String.valueOf(user.getAge()));
        dobField_Upd.setText(user.getDate());
        emailField_Upd.setText(user.getEmail());
        passField_Upd.setText(user.getPassword());
        phoneField_Upd.setText(user.getPhone());
        genderField_Upd.setText(user.getGender());
        typeField_Upd.setText(user.getType());
        addressField_Upd.setText(user.getAddress());
        usernameField_Upd.setText(user.getUserName());
        updatePanel.toFront();
      }
    }catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  public void editBtn(ActionEvent event){
    try {
      ManageEmployee manageEmployee = new ManageEmployee();
      manageEmployee.editEmployee(firstnameField_Upd.getText(),lastnameField_Upd.getText(),Integer.parseInt(ageField_Upd.getText()),
              dobField_Upd.getText(),addressField_Upd.getText(),phoneField_Upd.getText(),emailField_Upd.getText(),passField_Upd.getText(),usernameField_Upd.getText(),
              typeField_Upd.getText(),genderField_Upd.getText(),Integer.parseInt(idField.getText()));
      clearUpdateField();
      initialize(null,null);
      panelEmployee.toFront();
    }catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  public void removeEmployee(ActionEvent event){
    try {
      ManageEmployee manageEmployee = new ManageEmployee();
      if(manageEmployee.checkID(Integer.parseInt(idTextField.getText()))){
        manageEmployee.deleteEmployee(Integer.parseInt(idTextField.getText()));
        System.out.println("Can delete");
        initialize(null,null);
      }else{
        System.out.println("Cannot delete");
      }
    }catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
  public void addEmployee(ActionEvent event) throws Exception {
    ManageEmployee manageEmployee = new ManageEmployee();
    User user = new User();
    user.setFirstName(firstnameField.getText());
    user.setLastName(lastnameField.getText());
    user.setAge(ageSpinner.getValue());
    user.setDate(dateField.getText());
    user.setAddress(addressField.getText());
    user.setPhone(telephoneField.getText());
    user.setEmail(emailField.getText());
    user.setUserName(usernameField.getText());
    user.setPassword(user.getUserName());
    user.setType("Employee");
    if(genderRB_M.isSelected()){
      user.setGender(genderRB_M.getText());
    }else{
      user.setGender(genderRB_F.getText());
    }
    manageEmployee.insertEmployee(user.getFirstName(),user.getLastName(),user.getAge(),user.getDate(),user.getAddress(),user.getPhone(),user.getEmail()
            ,user.getPassword(),user.getUserName(),user.getType(),user.getGender());
    initialize(null,null);
    clearTextField();
  }
  public void handleClicks(ActionEvent event) throws IOException {
    if(event.getSource() == employeeBtnEmp){
      panelEmployee.toFront();
    }else if(event.getSource() == productBtn){
      panelProduct.toFront();
    }else if(event.getSource() == salesBtn){
      panelSales.toFront();
    }else if(event.getSource() == settingBtn) {
      panelSetting.toFront();
    }else if(event.getSource() == backBtn_UpdEmp){
      panelEmployee.toFront();
    }else if(event.getSource() == logoutBtn){
      root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
      stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
  }



  //Buttons
  @FXML private Button employeeBtnEmp;
  @FXML private Button updateBtnEmp;
  @FXML Button backBtn_UpdEmp;
  @FXML private Button logoutBtn;
  @FXML private Button productBtn;
  @FXML private Button salesBtn;
  @FXML private Button settingBtn;

  //Anchor Pane
  @FXML private AnchorPane panelEmployee;
  @FXML private AnchorPane panelProduct;
  @FXML private AnchorPane panelSales;
  @FXML private AnchorPane updatePanel;
  @FXML private AnchorPane panelSetting;

  //Add Field
  @FXML private TextField addressField;
  @FXML private TextField emailField;
  @FXML private TextField firstnameField;
  @FXML private TextField lastnameField;
  @FXML private TextField telephoneField;
  @FXML private TextField dateField;
  @FXML private TextField usernameField;
  @FXML private TextField idTextField;
  @FXML private RadioButton genderRB_M,genderRB_F;

  //Update Field
  @FXML private TextField ageField_Upd;
  @FXML private TextField dobField_Upd;
  @FXML private TextField emailField_Upd;
  @FXML private TextField firstnameField_Upd;
  @FXML private TextField genderField_Upd;
  @FXML private TextField idField;
  @FXML private TextField lastnameField_Upd;
  @FXML private TextField passField_Upd;
  @FXML private TextField phoneField_Upd;
  @FXML private TextField typeField_Upd;
  @FXML private TextField usernameField_Upd;
  @FXML private TextField addressField_Upd;
  //User Table Component
  @FXML private TableView<User> table;
  @FXML private TableColumn<User, String> address;
  @FXML private TableColumn<User, Integer> age;
  @FXML private TableColumn<User, String> date;
  @FXML private TableColumn<User, String> email;
  @FXML private TableColumn<User, String> firstName;
  @FXML private TableColumn<User, String> gender;
  @FXML private TableColumn<User, Integer> id;
  @FXML private TableColumn<User, String> lastName;
  @FXML private TableColumn<User, String> password;
  @FXML private TableColumn<User, String> phone;
  @FXML private TableColumn<User, String> type;
  @FXML private TableColumn<User, String> userName;
  @FXML private TextField searchFilter;
  @FXML private Spinner<Integer> ageSpinner;
  //Product Table Component
  @FXML private TableView<Product> pTable;
  @FXML private TableColumn<Product,Integer> pID;
  @FXML private TableColumn<Product,String> pName;
  @FXML private TableColumn<Product,Double> pPrice;
  @FXML private TableColumn<Product,Integer> pQty;
}
