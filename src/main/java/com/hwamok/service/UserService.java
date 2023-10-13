package com.hwamok.service;


import com.hwamok.controller.dto.MailDto;
import com.hwamok.entity.User;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface UserService {
    User changeProfile(String email, String name, String password, String birthday, MultipartFile imageFile) throws IOException;

    void withdraw(String email);
    User findEmail(String name, String birthday) throws RuntimeException;

    Map<String, String> findEmailvalidateHandling(Errors errors);

    User NewPassword(String name, String email) throws RuntimeException;

}
