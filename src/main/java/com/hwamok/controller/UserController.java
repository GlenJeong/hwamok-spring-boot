package com.hwamok.controller;

import com.hwamok.entity.User;
import com.hwamok.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change-profile")
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
}
