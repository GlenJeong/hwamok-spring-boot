package com.hwamok.controller;

import com.hwamok.controller.dto.NoticeBoardCreateDTO;
import com.hwamok.service.NoticeBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoticeBoardController {

    private NoticeBoardService noticeBoardService;

    public NoticeBoardController(NoticeBoardService noticeBoardService) {
        this.noticeBoardService = noticeBoardService;
    }

    @PostMapping("/noticeBoard")
    public String noticeBoard(NoticeBoardCreateDTO dto){
        noticeBoardService.addNoticeBoard(dto);

        return "index";
    }
}
