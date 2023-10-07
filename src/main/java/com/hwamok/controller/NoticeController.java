package com.hwamok.controller;

import com.hwamok.controller.dto.NoticeCreateDTO;
import com.hwamok.entity.User;
import com.hwamok.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class NoticeController {

    private NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/noticeWrite")
    public String noticeForm(){

        return "notice-write";
    }

    @PostMapping("/noticeWrite")
    public String createNotice(NoticeCreateDTO dto, HttpSession session){
        User user = (User) session.getAttribute("user");

        noticeService.createNotice(dto, user);

        return "redirect:/noticeList";

        // 숙제: 수정 삭제
    }
    // 하나의 Notice를 가져오는 방법이
    // GET을 써야 할 것 같음
    // 키 값(id)을 알아야 함
    // 주소에 notice의 id가 1번인 노티스 가져다줘
    // QueryString = > ?noticeId=1

    // {id} => PathVariable 많이 사용하는 추세, 특정 URL의 식별을 위해서 사용


    @GetMapping("/noticeDelete")
    public String noticeDelete(Long id){
        noticeService.noticeDelete(id);

        return "redirect:/noticeList";
    }

    @GetMapping("/noticeList")
    public String findAll(Model model){
        // DB에서 전체 게시글 데이터를 프론트로 가져와서 Model에 담아서 notice-list.html에 보여준다.
        // List<NoticeCreateDTO> noticeCreateDTOList = noticeService.findAll();

        model.addAttribute("notice", noticeService.findAll());
        return "notice-list";
    }

    @GetMapping("/noticeView/{id}")
    public String noticeView(@PathVariable Long id, Model model){
        model.addAttribute("view",noticeService.noticeView(id));

        return "notice-view";
    }

    @GetMapping("/noticeEdit/{id}")
    public String noticeEdit(@PathVariable Long id, Model model){
        model.addAttribute("edit",noticeService.noticeEdit(id));

        return "notice-edit";
    }

    @PostMapping("/noticeUpdate/{id}")
    public String noticeUpdate(@PathVariable Long id, NoticeCreateDTO dto){
        noticeService.noticeUpdate(id, dto.getTitle(), dto.getContent());

        return "redirect:/noticeList";
    }
}


// 수정을 하기 위해서는 먼저 하나의 게시글을 먼저 조회
// 수정하기 위해서 필요한 것이 무엇일까?
// 주소 입력해서 들어가기(O)
// List로 일단 출력 후 수정 클릭하기(V), 게시판의 최종 목적
// 하드 코딩으로 넣어버리기


// th:object={notice}