package com.hwamok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.ui.Model;

@Controller
public class RouteController {

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
  public String signInPage(@CookieValue(name = "email", required = false) String email, Model model) {

    model.addAttribute("email", email);
    return "sign-in";
  }

  @GetMapping("/sign-up")
  public String signUpPage() {
    return "sign-up";
  }

  @GetMapping("/ui-notice")
  public String board() {
    return "redirect:/noticeList/1";
  }

  @GetMapping("/my-page")
  public String myPage() {
    return "ui-mypage";
  }

  @GetMapping("/noticepage")
  public String noticePage(){
    return "notice";
  }

  @GetMapping("/find-Account")
  public String findAccount(){
    return "find-Account";
  }

  @GetMapping("/find-Password")
  public String findPassword(){
    return "find-Password";
  }

  @GetMapping("/found-Email")
  public String findEmail(){
    return "found-Email";
  }

  @GetMapping("/found-Password")
  public String foundPassword() {
    return "found-Password";
  }

  @GetMapping("/file-upload")
  public String fileUpload() {
    return "fileUpload";
  }
}
