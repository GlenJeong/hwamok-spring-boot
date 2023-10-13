package com.hwamok.controller;

import com.hwamok.controller.dto.NoticeCreateDTO;
import com.hwamok.entity.Notice;
import com.hwamok.entity.User;
import com.hwamok.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class NoticeController {

    private NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notices") // 비동기 방식 처리
    @ResponseBody // 잭슨 메시지 컴버터부터 사용할 수 있게 바꿔줌
        public Page<Notice> getNotices(@RequestParam(required = false) String keyword, // 있을 수도 있고 없을 수도 있어서 required = false
                               @RequestParam(defaultValue = "1") int curPage,
                               @RequestParam(defaultValue = "3") int pageSize) {
        return noticeService.getNotices(keyword, curPage, pageSize);

    }


    @GetMapping("/noticeWrite")
    public String noticeForm(){

        return "notice-write";
    }

    @PostMapping("/noticeWrite")
    public String createNotice(NoticeCreateDTO dto,HttpSession session, MultipartFile file) throws IOException {
        User user = (User) session.getAttribute("user");
        // session에서 유저 정보를 가지고 와서 유저에 저장

        noticeService.createNotice(dto, user, file);

        return "redirect:/ui-notice";

        // 숙제: 수정 삭제
    }
    // 하나의 Notice를 가져오는 방법이
    // GET을 써야 할 것 같음
    // 키 값(id)을 알아야 함
    // 주소에 notice의 id가 1번인 노티스 가져다줘
    // QueryString = > ?noticeId=1

    // {id} => PathVariable 많이 사용하는 추세, 특정 URL의 식별을 위해서 사용


    @GetMapping("/noticeDelete/{id}")
    public String noticeDelete(@PathVariable Long id){
        noticeService.noticeDelete(id);

        return "notice";
    }

    // noticeList?curPage=4
    @GetMapping(value = {"/noticeList/{curPage}"})
    public String findAll(Model model, @PathVariable Integer curPage, @RequestParam(defaultValue = "5") int pageSize){
        // DB에서 전체 게시글 데이터를 프론트로 가져와서 Model에 담아서 notice-list.html에 보여준다.
        // List<NoticeCreateDTO> noticeCreateDTOList = noticeService.findAll();

        Page<Notice> noticeAll = noticeService.findAll(curPage, pageSize);
        System.out.println("curPage :::::::: " + curPage);
        // session에서 유저 정보를 가지고 와서 유저에 저장
        model.addAttribute("date", new Date());
        model.addAttribute("notice", noticeAll);
        model.addAttribute("currentPage", curPage);
        model.addAttribute("totalPages", noticeAll.getTotalPages());
        model.addAttribute("totalCount", noticeAll.getTotalElements());
        return "notice-list";
    }



    @GetMapping("/noticeView/{id}")
    public String noticeView(@PathVariable Long id, Model model){

        try {
            System.out.println("//////////////////////////////////////////////////////");
            Notice notice = noticeService.noticeView(id);
            model.addAttribute("view", notice);
        }catch (RuntimeException re){
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
            model.addAttribute("errorMessagesNotice", re.getMessage());
            return "errorMessagesNotice";
        }


        return "notice-view";
    }

    @GetMapping("/noticeEdit/{id}")
    public String noticeEdit(@PathVariable Long id, Model model){
        try{
            System.out.println("//////////////////////////////////////////////////////");
            Notice notice = noticeService.noticeView(id);
            model.addAttribute("edit", notice);
        }catch (RuntimeException re){
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
            model.addAttribute("errorMessagesNotice", re.getMessage());
            return "errorMessagesNotice";
        }

        return "notice-edit";
    }

    @PostMapping("/noticeUpdate/{id}")
    public String noticeUpdate(@PathVariable Long id, NoticeCreateDTO dto, HttpSession session, MultipartFile file)throws IOException{
        User user = (User) session.getAttribute("user");

        noticeService.noticeUpdate(id, dto.getTitle(), dto.getContent(), file, user.getName());


        return "redirect:/ui-notice";
    }
}


// 수정을 하기 위해서는 먼저 하나의 게시글을 먼저 조회
// 수정하기 위해서 필요한 것이 무엇일까?
// 주소 입력해서 들어가기(O)
// List로 일단 출력 후 수정 클릭하기(V), 게시판의 최종 목적
// 하드 코딩으로 넣어버리기


// th:object={notice}