package com.hwamok.controller;

import com.hwamok.controller.dto.MailDto;
import com.hwamok.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MailController {


    private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/mail")
    public String dispMail() {
        return "mail";
    }

    @PostMapping("/mail")
    public void execMail(MailDto mailDto) {
        System.out.println("execMail start mailDto = " + mailDto);
        System.out.println("mailDto.getTitle() = " + mailDto.getTitle());
        System.out.println("mailDto.getAddress() = " + mailDto.getAddress());

        mailService.mailSend(mailDto);
        System.out.println("execMail end mailDto = " + mailDto);


    }
}
