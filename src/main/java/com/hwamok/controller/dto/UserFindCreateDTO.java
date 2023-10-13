package com.hwamok.controller.dto;

import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserFindCreateDTO {

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "이름은 특수문자를 제외한 2~10자리여야 합니다.")
  private String name;

  @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", message = "년월일 형식(yyyy-MM-dd)에 맞지 않습니다") // yyyy-MM-dd만 받기 위한 정규표현식
  @NotBlank(message = "생일은 필수 입력 값입니다.")
  private String birthday;

  private String email;

  public UserFindCreateDTO(String name, String birthday, String email) throws Exception {
    this.name = name;
    this.birthday = birthday;
    this.email = email;
  }

  public String getName() {
    return name;
  }
  public String getBirthday() {
    return birthday;
  }
  public String getEmail() {
    return email;
  }
}
