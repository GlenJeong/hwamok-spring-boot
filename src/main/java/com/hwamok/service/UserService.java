package com.hwamok.service;


import com.hwamok.entity.User;

public interface UserService {
    User changeProfile(String email, String name, String password);

    void withdraw(String email);
}
