package com.hwamok.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserCreateDTO {

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "이름은 특수문자를 제외한 2~10자리여야 합니다.")
  private String name;
  @NotBlank(message = "이메일은 필수 입력 값입니다.")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
  private String email;
  @NotBlank(message = "비빌번호는 필수 입력 값입니다.")
  @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
  private String password;
  @NotBlank(message = "생일은 필수 입력 값입니다.")
  @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", message = "년월일 형식(yyyy-MM-dd)에 맞지 않습니다") // yyyy-MM만 받기 위한 정규표현식
  private String birthday;

  public UserCreateDTO(String name, String email, String password, String birthday) throws Exception {
    this.name = name;
    this.email = email;
    this.password = password;
    this.birthday = birthday;
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

  public String getBirthday() {
    return birthday;
  }
}
