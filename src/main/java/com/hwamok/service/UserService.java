package com.hwamok.service;

<<<<<<< HEAD
public interface UserService {
    void changeProfile(String email, String name, String password);

    void withdraw(String email);
=======
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void changeProfile(String email, MultipartFile multipartFile, String contextPath);
>>>>>>> origin/main
}
