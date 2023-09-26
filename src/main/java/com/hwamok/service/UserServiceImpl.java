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
        // 이름을 바꾸는 걸로 수정하기
        // 패스워드 바꾸기

        // 트랜잭션 거는 법
        // 1. PSA가 적용된 방식
        // 2. 직접 구현하기

        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("user not found"));
        // 널일 때 익셉션 발생


        try {
            // 방어 로직 설계
            if(password.isBlank()){
                throw new RuntimeException("invalidate password");
            }

            if(name.isBlank()){
                throw new RuntimeException("invalidate name");
            }

            user.changeName(name);
            user.changePassword(password);

            platformTransactionManager.commit(status);
        }catch (RuntimeException e){
            e.printStackTrace();
            platformTransactionManager.rollback(status);
        }
        // update user set name = ? --> 성공
        // update user set password = ? --> 실패

        // 트랜잭션이란? ACID
        // 원자성
        // 여러 개의 작업을 하나의 작업으로 묶어서 처리
        // A B C  3가지의 작업이 다 성공해야 정상적인 반영이 됨 => 전부 성공 ==> Commit
        // 3가지 중 하나라도 실패한다면 전부 실패처리 ==> Rollback

    }

    @Override
    public void withdraw(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));
        userRepository.delete(user);

    }
}
