package com.hwamok.service;

import com.hwamok.controller.dto.NoticeCreateDTO;
import com.hwamok.entity.Notice;
import com.hwamok.entity.User;

import java.util.List;

public interface NoticeService {

    void createNotice(NoticeCreateDTO dto, User user);

    void noticeDelete(Long id);

    List<Notice> findAll();

    Notice noticeView(Long id);

    Notice noticeEdit(Long id);

    void noticeUpdate(Long id, String title, String content);
}
