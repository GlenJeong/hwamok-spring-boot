package com.hwamok.service;

import com.hwamok.controller.dto.NoticeBoardCreateDTO;
import com.hwamok.entity.NoticeBoard;
import com.hwamok.repository.NoticeBoardRepository;
import org.springframework.stereotype.Service;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService{

    private NoticeBoardRepository noticeBoardRepository;

    public NoticeBoardServiceImpl(NoticeBoardRepository noticeBoardRepository) {
        this.noticeBoardRepository = noticeBoardRepository;
    }



    @Override
    public void addNoticeBoard(NoticeBoardCreateDTO dto) {
        noticeBoardRepository.save(new NoticeBoard(dto.getTitle(), dto.getWriter(), dto.getPassword(), dto.getContent()));

    }
}
