package com.hwamok.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserPasswordCreateDto {

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "이름은 특수문자를 제외한 2~10자리여야 합니다.")
    private String name;
    private String password;

    @NotBlank(message = "이메일을 입력해주세요")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    public UserPasswordCreateDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password=password;

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
