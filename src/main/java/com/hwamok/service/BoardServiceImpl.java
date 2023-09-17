package com.hwamok.service;

import com.hwamok.controller.dto.BoardCreateDTO;
import com.hwamok.entity.Board;
import com.hwamok.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }



    @Override
    public void addBoard(BoardCreateDTO dto) {
        boardRepository.save(new Board(dto.getTitle(), dto.getContent()));
    }
}
