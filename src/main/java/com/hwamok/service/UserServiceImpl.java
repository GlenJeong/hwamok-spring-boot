package com.hwamok.service;

import com.hwamok.entity.User;
import com.hwamok.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

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
            
    }
}
