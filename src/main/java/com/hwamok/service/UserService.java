package com.hwamok.service;

public interface UserService {
    void changeProfile(String email, String name, String password);

    void withdraw(String email);
}
