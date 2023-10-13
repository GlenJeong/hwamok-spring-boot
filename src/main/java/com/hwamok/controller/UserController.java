package com.hwamok.controller;


import com.hwamok.controller.dto.ChangeProfileDTO;
import com.hwamok.controller.dto.MailDto;
import com.hwamok.controller.dto.UserFindCreateDTO;
import com.hwamok.controller.dto.UserPasswordCreateDto;
import com.hwamok.entity.User;
import com.hwamok.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Controller
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change-profile")
    public String changeProfile( @Valid ChangeProfileDTO changeProfileDTO, Errors errors, HttpSession session, MultipartFile imageFile, Model model) {

        System.out.println("UserController changeProfile changeProfileDTO.getBirthday() = " + changeProfileDTO.getBirthday());

        if(errors.hasErrors()){
            model.addAttribute("changeProfileDTO", changeProfileDTO); //회원가입 실패시 입력 데이터 값을 유지

            Map<String, String> validatorResult = userService.findEmailvalidateHandling(errors);
            validatorResult.entrySet();
            System.out.println("UserController changeProfile validatorResult = " + validatorResult);
            for (String keys : validatorResult.keySet()) {
                model.addAttribute(keys, validatorResult.get(keys));
               System.out.println(" signUp key = " + keys);



//        signUp key = valid_name
//        signUp key = valid_birthday

            }
            return "ui-mypage";
        }

        System.out.println(" UserController changeProfile imageFile = " + imageFile);
        // 유저의 이름과 패스워드 있으면 바꿔줌
        // 명시적 형변환
        User user = (User) session.getAttribute("user");
        // session에서 유저 정보를 가지고 와서 유저에 저장

        System.out.println("UserController changeProfile user.getEmail() = " + user.getEmail());
        System.out.println("UserController changeProfile dto.getName() = " + changeProfileDTO.getName());
        System.out.println("UserController changeProfile dto.getPassword() = " + changeProfileDTO.getPassword());
        System.out.println("UserController changeProfile dto.getBirthday() = " + changeProfileDTO.getBirthday());
        System.out.println("UserController changeProfile imageFile = " + imageFile);

        try {
            User changeUser = userService.changeProfile(user.getEmail(), changeProfileDTO.getName(), changeProfileDTO.getPassword(), changeProfileDTO.getBirthday(), imageFile);

            System.out.println("changeUser.getFilePath() = " + changeUser.getFilePath());
            session.setAttribute("user", changeUser);
            // 기존에 있던 세션 정보를 changeUser의 정보로 새로 생성한다.

            // 숙제: 마이페이지 수정하면 페이지 그대로인거
        }catch (RuntimeException | IOException e){
            e.getMessage();
            model.addAttribute("invalidate", e.getMessage());
            return "ui-mypage";
        }
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

    @PostMapping("/find-Password")
    public String findPassword(@Valid UserPasswordCreateDto userPasswordCreateDto, Errors errors, Model model, MailDto mailDto) {

        if(errors.hasErrors()) {
            model.addAttribute("userPasswordCreateDto", userPasswordCreateDto);

            Map<String, String> validationResult = userService.findEmailvalidateHandling(errors);
            for(String key: validationResult.keySet()){
                model.addAttribute(key, validationResult.get(key));

            }
            return "find-Password";
        }

        User newPassword;

        try {
            newPassword = userService.NewPassword(userPasswordCreateDto.getName(), userPasswordCreateDto.getEmail());
        }catch (RuntimeException e) {
            System.out.println("findPassword e.getMessage() = " + e.getMessage());

            model.addAttribute("invalidate", e.getMessage());
            return "find-password";
        }

        System.out.println("UserController findPassword findPassword.getPassword() = " + newPassword.getPassword());
        model.addAttribute("passowrd", newPassword);
        return "found-Password";
    }

    @PostMapping("/find-Account")
    public String findAccount(@Valid UserFindCreateDTO userFindCreateDTO, Errors errors, Model model) {

        if(errors.hasErrors()){
            model.addAttribute("userFindCreateDTO", userFindCreateDTO); //회원가입 실패시 입력 데이터 값을 유지

            Map<String, String> validatorResult = userService.findEmailvalidateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
                System.out.println(" signUp key = " + key);

//        signUp key = valid_name
//        signUp key = valid_birthday

            }
            return "find-Account";
        }


        User findEmail;

        try {
            findEmail = userService.findEmail(userFindCreateDTO.getName(), userFindCreateDTO.getBirthday());
        } catch (RuntimeException e){
            System.out.println("findAccount e.getMessage() = " + e.getMessage());
            model.addAttribute("invalidate", e.getMessage());
            return "find-Account";
        }


        model.addAttribute("findEmail", findEmail);
        return "found-Email";
    }

}
