package com.hwamok.service;

import com.hwamok.entity.User;
import com.hwamok.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    private PlatformTransactionManager platformTransactionManager;



    public UserServiceImpl(UserRepository userRepository, PlatformTransactionManager platformTransactionManager) {
        this.userRepository = userRepository;
        this.platformTransactionManager=platformTransactionManager;
    }

    @Override
    public User changeProfile(String email, String name, String password, MultipartFile imageFile) throws IOException {
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("not found user"));

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\profile";

        UUID uuid = UUID.randomUUID();
        // 파일에 붙일 랜덤한 식별자 만들기

        String saveName = uuid + "_" + imageFile.getOriginalFilename();

        String originalName = imageFile.getOriginalFilename();

        File saveFile = new File(projectPath, saveName);

        imageFile.transferTo(saveFile);








        try {
            if(name.isBlank()){
//                RuntimeException re = new RuntimeException("invalidate name");
//                throw re;
                throw new RuntimeException("invalidate name");
            }
            if(password.isBlank()){
                throw new RuntimeException("invalidate password");
            }
            user.changeName(name);
            user.changePassword(password);
            user.uploadFileName(saveName);
            user.uploadOrignalName(originalName);
            user.uploadFilePath("/images/profile/"+saveName);

            platformTransactionManager.commit(status);
        }catch (RuntimeException e){
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
}