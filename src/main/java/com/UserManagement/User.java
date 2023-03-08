package com.UserManagement;

public class User {
  private int userID;
  private String firstName;
  private String lastName;
  private int age;
  private String date;
  private String email;
  private String password;
  private String phone;
  private String gender;
  private String type; //admin or employee
  private String address;
  private String userName;
  private int is_active;

  public void setIs_active(int is_active) {
    this.is_active = is_active;
  }
  public int getIs_active(){
    return is_active;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public int getUserID() {
    return userID;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getAge() {
    return age;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDate() {
    return date;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setPassword(String userName) {
    this.password = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPhone() {
    return phone;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getGender() {
    return gender;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }
}