package com.hwamok.service;

import com.hwamok.entity.User;
import com.hwamok.repository.UserRepository;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
=======
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
>>>>>>> origin/main

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

<<<<<<< HEAD
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

=======
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void changeProfile(String email, MultipartFile multipartFile, String contextPath) {
        // 로그인한 유저가 파일페이지에서 사진을 업로드해서 프로필 사진을 바꿈
        // 유저 서비스가 알아야할 정보는 로그인 유저의 식별자(PK), 파일 == 이 정보를 가지고 오는 곳이 session일까?

        System.out.println(email);
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(contextPath);
        
        // 1. repository에서 이메일로 객체를 조회
        // 2. 파일에 대한 처리
        // 3. 리포지토리에서 조회한 객체에 파일이름을 넣주고
        // 4. DB에 저장
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found") );

        // 파일에 대한 처리, JAVA에서 파일 처리에 대해서 제공

        // 서버가 실행되면 jar 압축 -> 풀어서 사용
        System.out.println("::::::::::::::::::::::::::");
        File saveDirectory = new File(contextPath);

        // 파일을 처리할 때 서버에 저장용 이름 지정
        //

        if(!saveDirectory.exists()) {
            saveDirectory.mkdirs(); // makeDirectories 복수로 여러 개 만듬
            //saveDirectory.mkdir(); // makeDirectory
        }

        String originalName = multipartFile.getOriginalFilename();
        String saveName = null;

        try {
            String extention = originalName.substring(originalName.lastIndexOf("."));
            File destination = File.createTempFile("F_"+System.currentTimeMillis(), extention, saveDirectory);

            saveName = destination.getName();
            FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(destination));
        }catch (Exception e){
            e.printStackTrace();
        }

        user.changeProfile(originalName, saveName);


        // DB에서 저장된 것을 불러올 때 1차 캐시라는 곳에 엔티티를 복사해둠
        // 복사해둔 엔티티가 변경점이 있다면 DB에 반영을 해줌(Dirty checking) --> transaction이 commit

        // 서버안 저장
        // 1. 저희처럼 서버가 실제 뜬 주소를 얻어서 거기에 저장, 서버 재시작하면 날라감
        //  --> 어떻게 개발하나 보여주는 것임.
        // 2. 서버가 설치되어 있는 컴퓨터 외부에 폴더를 만들어서 저장, 서버 재시작해도 안 날라감
        // 3. 최신 트렌드는 클라우드에 저장해서 사용함 -> 저희가 프로젝트를 시작할 때 AWS 클라우드 서비스를 이요해서 함
        
        // 서버를 실행할 때 src 이하 모든 폴더를 jar 파일로 압축
        // 실행하면 tomcat으로 jar 압축을 풀어서 실행
        // tomcat 폴더안에 풀림
        // spring boot 내장되어 있어서 어떻게 동작하는 지 몰라서 확인 할 수 없음
        // spring framework에서는 외장톰캣 root에 풀림
            
>>>>>>> origin/main
    }
}
