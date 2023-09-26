package com.hwamok.controller;

import com.hwamok.controller.dto.NoticeCreateDTO;
import com.hwamok.entity.Notice;
import com.hwamok.entity.User;
import com.hwamok.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class NoticeController {

    private NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }


    @PostMapping("/noticeWrite")
    public String createNotice(NoticeCreateDTO dto, HttpSession session){
        User user = (User) session.getAttribute("user");

        noticeService.createNotice(dto, user);

        return "redirect:/noticeList";

        // 숙제: 수정 삭제

    }

    @GetMapping("/noticeDelete")
    public String noticeDelete(Notice notice, Long id){
        noticeService.noticeDelete(id);

        return "redirect:/noticeList";
    }

    @GetMapping("/noticeList")
    public String findAll(HttpSession session, Model model){
        // DB에서 전체 게시글 데이터를 프론트로 가져와서 Model에 담아서 notice-list.html에 보여준다.
        // List<NoticeCreateDTO> noticeCreateDTOList = noticeService.findAll();
        User user = (User) session.getAttribute("user");
        model.addAttribute("notice", noticeService.findAll());
        return "notice-list";
    }

    @GetMapping("/noticeForm")
    public String noticeForm(){

        return "notice-write";
    }

    @GetMapping("/noticeView")
    public String noticeView(Model model, Long id){
        model.addAttribute("view",noticeService.noticeView(id));

        return "notice-view";
    }

    @GetMapping("/noticeEdit")
    public String noticeEdit(Model model, Long id){
        model.addAttribute("edit",noticeService.noticeEdit(id));

        return "notice-edit";
    }

    @PostMapping("/noticeUpdate")
    public String noticeUpdate(Long id, NoticeCreateDTO dto){
        noticeService.noticeUpdate(id, dto.getTitle(), dto.getContent());

        return "redirect:/noticeList";
    }
}
