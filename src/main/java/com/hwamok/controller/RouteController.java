package com.hwamok.controller;

import com.hwamok.repository.SignRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

  private final SignRepository signRepository;

  public RouteController(SignRepository signRepository) {
    this.signRepository = signRepository;
  }

  @GetMapping("/ui-buttons")
  public String uiButtonsPage() {
    return "ui-buttons";
  }

  @GetMapping("/ui-alerts")
  public String uiAlertsPage() {
    return "ui-alerts";
  }

  @GetMapping("/ui-card")
  public String uiCardPage() {
    return "ui-card";
  }

  @GetMapping("/ui-forms")
  public String uiFormsPage() {
    return "ui-forms";
  }

  @GetMapping("/ui-typography")
  public String uiTypographyPage() {
    return "ui-typography";
  }

  @GetMapping("/sign-in")
  public String signInPage() {
    return "sign-in";
  }

  @GetMapping("/sign-up")
  public String signUpPage() {
    return "sign-up";
  }

  @GetMapping("/ui-notice")
  public String board() { return "redirect:/noticeList";  }


  @GetMapping("/my-page")
  public String myPage(){
    return "ui-mypage";
  }
}