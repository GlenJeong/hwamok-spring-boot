package com.hwamok.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void changeProfile(String email, MultipartFile multipartFile, String contextPath);
}
