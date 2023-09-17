package com.hwamok.controller.dto;

public class UserCreateDTO {
  private String name;
  private String email;
  private String password;

  public UserCreateDTO(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public String getName() {
    return name;
  }
  public String getEmail() {
    return email;
  }
  public String getPassword() {
    return password;
  }
}
