package com.hwamok.service;

import  com.hwamok.controller.dto.MailDto;
import com.hwamok.service.util.MailHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {
    private static final String FROM_ADDRESS = "jyb0226@naver.com";

    @Autowired
    private JavaMailSender mailSender;

    public void mailSend(MailDto mailDto) {
            try {
                MailHandler mailHandler = new MailHandler(mailSender);

                // 받는 사람
                mailHandler.setTo(mailDto.getAddress());
                // 보내는 사람
                mailHandler.setFrom(MailServiceImpl.FROM_ADDRESS);
                // 제목
                mailHandler.setSubject(mailDto.getTitle());
                // HTML Layout
                String htmlContent = "<p>" + mailDto.getMessage() +"<p> <img src='cid:sample-img'>";
                mailHandler.setText(htmlContent, true);
                // 첨부 파일
                //mailHandler.setAttach("newTest.txt", "static/originTest.txt");
                // 이미지 삽입
                //mailHandler.setInline("sample-img", "static/sample1.jpg");

                mailHandler.send();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

}
