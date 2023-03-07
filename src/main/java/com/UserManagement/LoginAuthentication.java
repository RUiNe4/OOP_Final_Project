package com.UserManagement;

import com.DatabaseFunction.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginAuthentication extends DBConnection {
    DBConnection con = new DBConnection();
    private final Statement statement;
    private final User user = new User();
  public LoginAuthentication() throws Exception {
    Connection connection = con.getConnection("jdbc:mysql://localhost:3306/possys", "root", "");
    this.statement = connection.createStatement();
  }
  public boolean authentication(String value,String password){
    boolean login = false;
    String qry = "select * from users where userName = \"" + value + "\" || " +
            "email = \"" + value + "\"";
    try{
      ResultSet rs = statement.executeQuery(qry);
      if(rs.next() == false){
        System.out.println("User not found");
      }else{
        user.setPassword(rs.getString("password"));
        if(password.equals(user.getPassword())){
          login = true;
          user.setUserID(rs.getInt("userID"));
          user.setFirstName(rs.getString("firstName"));
          user.setLastName(rs.getString("lastName"));
          user.setAge(rs.getInt("age"));
          user.setDate(rs.getString("dateOfBirth"));
          user.setEmail(rs.getString("email"));
          user.setPhone(rs.getString("phone"));
          user.setGender(rs.getString("gender"));
          user.setType(rs.getString("type"));
          user.setAddress(rs.getString("address"));
          user.setUserName(rs.getString("userName"));
        }else{
          System.out.println("Wrong password");
        }
      }
    } catch (
            SQLException e) {
      throw new RuntimeException(e);
    }
    return login;
  }

  public User getUser() {
    return user;
  }
}
