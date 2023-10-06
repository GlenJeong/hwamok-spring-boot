package com.hwamok.service;

import com.hwamok.entity.User;
import com.hwamok.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;


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
    public void changeProfile(String email, String name, String password) {
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("notice not found"));

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

            platformTransactionManager.commit(status);
        }catch (RuntimeException e){
            e.printStackTrace();
            platformTransactionManager.rollback(status);
        }
    }

    @Override
    public void withdraw(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found") );
        userRepository.delete(user);
    }
}