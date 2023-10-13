package com.hwamok.service;

import com.hwamok.controller.dto.MailDto;

public interface MailService {

    void mailSend(MailDto mailDto);
}
