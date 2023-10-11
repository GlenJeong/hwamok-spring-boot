package com.hwamok.controller;

import com.hwamok.entity.Notice;
import com.hwamok.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RouteController {

  private NoticeService noticeService;

  public RouteController(NoticeService noticeService) {
    this.noticeService = noticeService;
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
}
