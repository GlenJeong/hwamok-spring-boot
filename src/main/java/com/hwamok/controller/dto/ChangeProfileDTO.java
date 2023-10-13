package com.hwamok.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ChangeProfileDTO {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "이름은 특수문자를 제외한 2~10자리여야 합니다.")
    private String name;
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
//    @NotBlank(message = "비빌번호는 필수 입력 값입니다.")

    private String password;

    @NotBlank(message = "생일은 필수 입력 값입니다.")
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", message = "년월일 형식(yyyy-MM-dd)에 맞지 않습니다") // yyyy-MM만 받기 위한 정규표현식
    private String birthday;

    public ChangeProfileDTO(String name, String password, String birthday) {
        this.name = name;
        this.password = password;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }
}
