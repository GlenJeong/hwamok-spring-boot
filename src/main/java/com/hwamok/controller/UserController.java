package com.hwamok.controller;

<<<<<<< HEAD
import com.hwamok.controller.dto.ChangeProfileDTO;
import com.hwamok.entity.User;
import com.hwamok.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

=======
import com.hwamok.entity.User;
import com.hwamok.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
>>>>>>> origin/main

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change-profile")
<<<<<<< HEAD
    public String changeProfile(HttpSession session, ChangeProfileDTO dto){
        // 유저의 이름과 패스워드 있으면 바꿔줌
        // 명시적 형변환
        User user = (User) session.getAttribute("user");
        // session에서 유저 정보를 가지고 와서 유저에 저장

        userService.changeProfile(user.getEmail(), dto.getName(), dto.getPassword());

        System.out.println("user.getName() = " + user.getName());
        System.out.println("dto.getName() = " + dto.getName());

        user.setName(dto.getName());


        // 숙제: 마이페이지 수정하면 페이지 그대로인거

        //model.addAttribute("userName", dto.getName()); // Model m 추가
        return "ui-mypage";
    }

    @GetMapping("/withdraw")
    public String withdraw(HttpSession session){
        User user = (User) session.getAttribute("user");


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
    
=======
    public void changeProfile( MultipartHttpServletRequest request){
        // Spring의 요청을 처리하는 방식 2가지
        // 1번째 방식은 기본으로 설정되어 있는 방식 => HttpServletRequest
        // 첫번째 방식의 단점은 통로가 너무 좁음 
        // HttpServletRequest 이 안에 세션이 있음
        
        // 명시적 형변환
        User user = (User) request.getSession().getAttribute("user");
        // session에서 유저 정보를 가지고 와서 유저에 저장

        userService.changeProfile(user.getEmail(), request.getFile("imageFile"), request.getSession().getServletContext().getRealPath("/file"));

    }
>>>>>>> origin/main
}
