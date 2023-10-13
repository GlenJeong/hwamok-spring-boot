package com.hwamok.service;


import com.hwamok.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    User changeProfile(String email, String name, String password, MultipartFile imageFile) throws IOException;

    void withdraw(String email);
}
