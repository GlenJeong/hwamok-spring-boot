package com.hwamok.controller;


import com.hwamok.controller.dto.ChangeProfileDTO;
import com.hwamok.entity.User;
import com.hwamok.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change-profile")
    public String changeProfile(HttpSession session, ChangeProfileDTO dto, MultipartFile imageFile)throws IOException {

        System.out.println("imageFile = " + imageFile);
        // 유저의 이름과 패스워드 있으면 바꿔줌
        // 명시적 형변환
        User user = (User) session.getAttribute("user");
        // session에서 유저 정보를 가지고 와서 유저에 저장

        User changeUser = userService.changeProfile(user.getEmail(), dto.getName(), dto.getPassword(), imageFile);

        System.out.println("changeUser.getFilePath() = " + changeUser.getFilePath());
        session.setAttribute("user", changeUser);
        // 기존에 있던 세션 정보를 changeUser의 정보로 새로 생성한다.

        // 숙제: 마이페이지 수정하면 페이지 그대로인거
        return "ui-mypage";
    }

    @GetMapping("/withdraw")
    public String withdraw(HttpSession session){
        User user = (User) session.getAttribute("user");
        // session에서 유저 정보를 가지고 와서 유저에 저장


        userService.withdraw(user.getEmail());
        session.invalidate();

        return "redirect:/";
    }
    // SignController, UserController
    // SignController: 회원가입(Insert), 로그인(Select), 로그아웃(Session)
    // UserController: 정보수정(Update), 탈퇴(Delete)
    // C(create), R(read), U, D


    // 회원 등록 ==> 기존에는 엑셀정리(500만명) --> 엑셀을 업로드하면 그 안에 데이터 회원가입 일괄등록 기능 시킴
    // 공지사항 게시판
    // C: 글쓰기(공지사항 등록) --> 여러개 한번에 등록
    // R: 게시판 글 읽기
    // U: 게시판 수정 --> 한번에 수정
    // D: 게시판 삭제 --> 한번에 삭제
    
    // R 대충 3가지로 나눔
    // 단건 조회, 리스트조회 List Collection, 페이징조회 Pageable(JPA)
}
