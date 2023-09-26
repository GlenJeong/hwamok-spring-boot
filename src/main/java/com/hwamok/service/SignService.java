package com.hwamok.service;

import com.hwamok.controller.dto.UserCreateDTO;
import com.hwamok.entity.User;

public interface SignService {
  // 제목 정해주고 구현은 따로 알아서 하세요
  void signUp(UserCreateDTO dto);

  User signIn(String email, String password);

    String emailCheck(String email);
}
