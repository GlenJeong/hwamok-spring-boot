package com.hwamok.controller.dto;

public class ChangeProfileDTO {
    private String name;
    private String password;

    public ChangeProfileDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
