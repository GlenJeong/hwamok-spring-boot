package com.hwamok.service;

import com.hwamok.controller.dto.BoardCreateDTO;
import com.hwamok.controller.dto.NoticeBoardCreateDTO;

public interface NoticeBoardService {
    void addNoticeBoard(NoticeBoardCreateDTO dto);
}
