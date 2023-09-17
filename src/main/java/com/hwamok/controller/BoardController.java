package com.hwamok.controller;

import com.hwamok.controller.dto.BoardCreateDTO;
import com.hwamok.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }



    @PostMapping("/boards")
    public String addBoard(BoardCreateDTO dto){
     boardService.addBoard(dto);

     return "index";
    }
}
