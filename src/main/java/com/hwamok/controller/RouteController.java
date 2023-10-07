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
    return "redirect:/noticeList";
  }

  @GetMapping("/my-page")
  public String myPage() {
    return "ui-mypage";
  }

  @GetMapping("/noticepage")
  public String noticePage(Model model, @RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "2") int pageSize){
    Page<Notice> notices = noticeService.getNotices(curPage, pageSize);

    // 현재가 내가 보고 있는 페이지(Current page), 쿼리로 조회, select * from notice limit 2 offset 2
    // 한 페이지에 몇 개를 보여줄건지(ItemPerPage), 쿼리로 조회
    // 노티스의 총 갯수(Total Count)

    model.addAttribute("notices", notices.getContent());
    model.addAttribute("currentPage", curPage);
    model.addAttribute("totalPages", notices.getTotalPages());
    model.addAttribute("totalCount", notices.getTotalElements());
    return "notice";
  }
}
