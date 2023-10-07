package com.hwamok.controller;

import com.hwamok.controller.dto.SignInDTO;
import com.hwamok.controller.dto.UserCreateDTO;
import com.hwamok.entity.User;
import com.hwamok.service.SignService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

@Controller
public class SignController {

  private SignService signService;

  public SignController(SignService signService) {
    this.signService = signService;
  }


  @PostMapping("/email-check")
  public @ResponseBody String emailCheck(@RequestParam("email") String email){
    System.out.println("email = " + email);
    String checkResult = signService.emailCheck(email);
    System.out.println("checkResult = " + checkResult);
    return checkResult;

//    if(checkResult != null){
//      return "ok";
//    }else {
//      return "no";
//    }
  }

  @PostMapping(value = "/sign-up")
  public String signUp(UserCreateDTO dto) {
    if(dto.getName().isBlank()){
      throw new RuntimeException("invalidate name");
    }

    if(dto.getEmail().isBlank()){
      throw new RuntimeException("invalidate email");
    }

    if(dto.getPassword().isBlank()){
      throw new RuntimeException("invalidate password");
    }
    // Spring은 Message Converter가 있어서 기본적으로 앞에 패스 붙히고 뒤에 .html
    // JSON으로 요청 응답을 주고받고함 --> Jackson Message Converter로 바꿔줘야 함
    // Spring의 모든 요청과 응답은 기본적으로 동기통신
    // Json으로 보내는 요청은 비동기 통신
    signService.signUp(dto);

    return "redirect:/";

  }

  //로그인 구현
  // 로그인은 조회 생성 수정 삭제
  // Http Method: POST
  // 로그인은 GET으로 해야하지만

  @PostMapping("/sign-in")
  public String signIn(SignInDTO dto, HttpSession session){
    // @RequestParam 쿼리 스트링을 받겠다는 의미함 ? key=value email이 key이고 value는 email안에 값이다.

    User user = signService.signIn(dto.getEmail(), dto.getPassword());

    session.setAttribute("user", user);
    //session.setMaxInactiveInterval(60); // 세션 유효시간, 기본이 초단위, 60는 60초를 의미





    // SOLID 원칙 (OOP의 설계 원칙)
    // OOP로 좋은 설계를 한다. ==> SOLID원칙을 잘 지켰다.
    // 객체는 한 가지의 일만 수행할 책임이 있다.
    // 단일 책임의 원칙
    // single responsibility principal
    // 개방 폐쇄 원칙(Open Close Principal)
    // 변경에는 폐쇄적이고 사용하는 곳에서는 유연하게 사용한다. -> OCP는 interface을 의미함
    // 변경에는 폐쇄적: Interface, 사용하는 곳에서 유연: Interface을 구현하는 곳
    
    //다미터의 법칙 ==> 최소한의 정보만을 알아야 한다.
    
    // 사이드 이펙트가 발생할 수도 있다.
    // 수정한 기능과 관계없는 다른 기능에서 오류가 발생

    // if는 LAZY 연산 ==> 게으른 연산

    //로그인을 유지하는 데 크게 3가지 방법이 있다.
    // 1. 세션 => 서버에 있는 가상의 공간 서버가 죽으면 세션도 사라짐, 접근이 불가능 서버 혹은 템플릿엔진만 접근 가능
    // 2. 쿠키 ==> 사용자 컴퓨터에 저장해둠, 보안상 취약해서 위험하다.  
    // 3. jwt ==> 나중에 설명

    return "redirect:/";

  }

  @GetMapping("/sign-out")
  public String signOutPage(HttpSession session) {
    session.invalidate(); // 세션 자체를 무효화 시킴
    // session.removeAttribute("user"); 사용자 정보를 제거함
    return "redirect:/";
  }
}
