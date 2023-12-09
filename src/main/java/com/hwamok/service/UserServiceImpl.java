package com.hwamok.service;

import com.hwamok.controller.dto.MailDto;
import com.hwamok.entity.User;
import com.hwamok.repository.UserRepository;
import com.hwamok.service.util.MailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private JavaMailSender mailSender;

    private static final String FROM_ADDRESS = "jyb0226@naver.com";


    public UserServiceImpl(UserRepository userRepository, PlatformTransactionManager platformTransactionManager) {
        this.userRepository = userRepository;
        this.platformTransactionManager=platformTransactionManager;
    }

    @Override
    public User changeProfile(String email, String name, String password, String birthday, MultipartFile imageFile) throws IOException {
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());

        System.out.println("UserServiceImpl password = " + password);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("not found user"));

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\profile";

        UUID uuid = UUID.randomUUID();
        // 파일에 붙일 랜덤한 식별자 만들기

        String saveName = uuid + "_" + imageFile.getOriginalFilename();

        String originalName = imageFile.getOriginalFilename();


        if (name.isBlank()) {
//                RuntimeException re = new RuntimeException("invalidate name");
//                throw re;
            throw new RuntimeException("이름을 입력해주세요.");
        }
        if (password.isBlank()) {
            throw new RuntimeException("비밀번호를 입력해주세요.");
        }

        if (birthday.isBlank()) {
            throw new RuntimeException("생일을 입력해주세요.");
        }

        try {

            user.changeName(name);
            user.changePassword(password);
            user.changeBirthday(birthday);

            if(imageFile.getOriginalFilename().equals("")){
                user.uploadFileName("");
                user.uploadOrignalName("");
                user.uploadFilePath("");
            } else {
                File saveFile = new File(projectPath, saveName);

                imageFile.transferTo(saveFile);

                user.uploadFileName(saveName);
                user.uploadOrignalName(originalName);
                user.uploadFilePath("/images/profile/" + saveName);

            }

            platformTransactionManager.commit(status);
        } catch (RuntimeException e){
            e.printStackTrace();
            platformTransactionManager.rollback(status);
        }
        return user;
    }

    @Override
    public void withdraw(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Not Found User") );
        userRepository.delete(user);
    }

    @Override
    public User findEmail(String name, String birthday) throws RuntimeException {
        System.out.println(" UserServiceImpl findEmail name = " + name);
        System.out.println(" UserServiceImpl findEmail birthday = " + birthday);



        if(name.isBlank()){
            throw new RuntimeException("이름을 입력해주세요.");
        }

        if(birthday.isBlank()){
            throw new RuntimeException("생일을 입력해주세요.");
        }


        User user = userRepository.findByNameAndBirthday(name, birthday).orElseThrow(()->new RuntimeException("일치하는 정보가 없습니다."));
        System.out.println(" findEmail user.getBirthday() = " + user.getBirthday());
        return userRepository.findByEmail(user.getEmail()).orElseThrow(()-> new RuntimeException("Not Found User"));

    }

    @Override
    public User NewPassword(String name, String email) throws RuntimeException {

        if(name.isBlank()) {
            throw new RuntimeException("이름을 입력해주세요.");
        }

        if(email.isBlank()) {
            throw new RuntimeException("이메일을 입력해주세요.");
        }

        User user = userRepository.findByNameAndEmail(name, email).orElseThrow(()->new RuntimeException("일치하는 정보가 없습니다."));
        UUID uuid = UUID.randomUUID();
        String newPassword = String.valueOf(uuid);
        System.out.println("newPassword = " + newPassword);
        user.updatePassword(newPassword);

        try {
            MailHandler mailHandler = new MailHandler(mailSender);

            // 받는 사람
            mailHandler.setTo(user.getEmail());
            // 보내는 사람
            mailHandler.setFrom(UserServiceImpl.FROM_ADDRESS);
            // 제목
            mailHandler.setSubject("임시 비밀번호입니다.");
            // HTML Layout
            String htmlContent = "<p>" + user.getPassword() +"<p> <img src='cid:sample-img'>";
            mailHandler.setText(htmlContent, true);

            mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return userRepository.findByPassword(user.getPassword()).orElseThrow(()->new RuntimeException("비밀번호가 없습니다."));
    }
}