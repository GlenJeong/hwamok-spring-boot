package com.hwamok.service;

import com.hwamok.controller.dto.UserCreateDTO;
import com.hwamok.entity.User;
import org.springframework.validation.Errors;

import java.text.ParseException;
import java.util.Map;

public interface SignService {
  // 제목 정해주고 구현은 따로 알아서 하세요
  void signUp(UserCreateDTO dto) throws Exception;

  User signIn(String email, String password);

  String emailCheck(String email);

  Map<String, String> validateHandling(Errors errors);

}
