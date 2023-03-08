package com.UserManagement;

import com.DatabaseFunction.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ManageEmployee extends DBConnection {
  DBConnection con = new DBConnection();
  private final Statement statement;
  private final User user = new User();
  Connection connection;
  public ManageEmployee() throws Exception {
    connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
    this.statement = connection.createStatement();
  }
  public User getUserByActive() throws SQLException {
    String sql = "SELECT * FROM users WHERE is_active=1";
    User user = new User();
    ResultSet resultSet = statement.executeQuery(sql);
    while (resultSet.next()){
      user.setUserID(resultSet.getInt("userID"));
      user.setFirstName(resultSet.getString("firstName"));
      user.setLastName(resultSet.getString("lastName"));
      user.setAge(resultSet.getInt("age"));
      user.setDate(resultSet.getString("dateOfBirth"));
      user.setEmail(resultSet.getString("email"));
      user.setPassword(resultSet.getString("password"));
      user.setPhone(resultSet.getString("phone"));
      user.setGender(resultSet.getString("gender"));
      user.setType(resultSet.getString("type"));
      user.setAddress(resultSet.getString("address"));
      user.setUserName(resultSet.getString("userName"));
      user.setIs_active(resultSet.getInt("is_active"));
    }
    return user;
  }

  public ObservableList<User> fetchUser(){
  ObservableList <User> userList = FXCollections.observableArrayList();
    try {
      String qry = "select * from users";
      ResultSet rs = statement.executeQuery(qry);
      while (rs.next()){
        User user = new User();
        user.setUserID(rs.getInt("userID"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setAge(rs.getInt("age"));
        user.setDate(rs.getString("dateOfBirth"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setGender(rs.getString("gender"));
        user.setType(rs.getString("type"));
        user.setAddress(rs.getString("address"));
        user.setUserName(rs.getString("userName"));
        userList.add(user);
      }
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return userList;
  }

  public boolean checkID(int ID) throws SQLException {
    boolean searchFound = false;
    String sql = "SELECT * FROM users WHERE userID=?";

    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(1, ID);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      System.out.println("Found");
      searchFound = true;
    }
    return searchFound;
  }

  public User getUser(int ID) throws SQLException {
    String sql = "SELECT * FROM users WHERE userID=?";
    User user = new User();
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(1, ID);
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()){
      user.setUserID(resultSet.getInt("userID"));
      user.setFirstName(resultSet.getString("firstName"));
      user.setLastName(resultSet.getString("lastName"));
      user.setAge(resultSet.getInt("age"));
      user.setDate(resultSet.getString("dateOfBirth"));
      user.setEmail(resultSet.getString("email"));
      user.setPassword(resultSet.getString("password"));
      user.setPhone(resultSet.getString("phone"));
      user.setGender(resultSet.getString("gender"));
      user.setType(resultSet.getString("type"));
      user.setAddress(resultSet.getString("address"));
      user.setUserName(resultSet.getString("userName"));
    }
    return user;
  }

  public void editEmployee(String firstName,String lastName,int age,String date,
                           String address,String telephone,String email,String password,String userName,String types,String gender,int ID) throws SQLException {
    String sql = "UPDATE users set firstName = '" + firstName + "' ,lastName = '" + lastName + "', age = '"
            + age + "',dateOfBirth = '" + date + "',email = '" +email+ "',password = '" + password+ "', phone = '" +telephone+"',gender ='"
            + gender + "',type = '" + types+ "', address = '" +address+ "',userName = '" + userName + "' WHERE userID = '" + ID + "'";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.execute();
  }

  public void editActive(int ID,int is_active) throws SQLException {
    String sql = "UPDATE users set is_active = '" + is_active + "' WHERE userID = '" + ID + "'";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.execute();
  }

  public void deleteEmployee(int ID) throws SQLException {
    String sql = "DELETE FROM Users WHERE userID=?";

    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(1, ID);

    int rowsDeleted = statement.executeUpdate();
    if (rowsDeleted > 0) {
      System.out.println("A user was deleted successfully!");
    }
  }
  public void insertEmployee(String firstName,String lastName,int age,String date,
                             String address,String telephone,String email,String password,String userName,String types,String gender)
  {
      Date date1 = Date.valueOf(date);
    try {
      String qry = "INSERT into users (firstName,lastName,age,dateOfBirth,email,password,phone,gender,type,address,userName)"
              + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      PreparedStatement psmt = connection.prepareStatement(qry);
      psmt.setString(1,firstName);
      psmt.setString(2,lastName);
      psmt.setInt(3,age);
      psmt.setDate(4,date1);
      psmt.setString(5,email);
      psmt.setString(6,password);
      psmt.setString(7,telephone);
      psmt.setString(8,gender);
      psmt.setString(9,types);
      psmt.setString(10,address);
      psmt.setString(11,userName);
      psmt.execute();
    }catch (Exception e){
      System.out.println(e);
    }
  }
}
