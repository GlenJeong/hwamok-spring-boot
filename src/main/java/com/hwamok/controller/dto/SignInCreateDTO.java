package com.hwamok.controller.dto;

public class SignInCreateDTO {

    private String email;
    private String password;

    public SignInCreateDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
