package com.hwamok.service;

public interface EmailService {

    void sendEmail(String toEmail, String title, String text);
}
