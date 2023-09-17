package com.hwamok.controller;

import com.hwamok.controller.dto.UserCreateDTO;
import com.hwamok.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignController {

  private SignService signService;

  public SignController(SignService signService) {
    this.signService = signService;
  }

  @PostMapping(value = "/sign-up")
  public String signUp(UserCreateDTO dto) {
    // Spring은 Message Converter가 있어서 기본적으로 앞에 패스 붙히고 뒤에 .html
    // JSON으로 요청 응답을 주고받고함 --> Jackson Message Converter로 바꿔줘야 함
    // Spring의 모든 요청과 응답은 기본적으로 동기통신
    // Json으로 보내는 요청은 비동기 통신
    signService.signUp(dto);

    return "sign-up";
  }
}
