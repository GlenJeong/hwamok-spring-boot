package com.hwamok.service;

import com.hwamok.controller.dto.NoticeCreateDTO;
import com.hwamok.entity.Notice;
import com.hwamok.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoticeService {

    void createNotice(NoticeCreateDTO dto, User user);

    Page<Notice> getNotices(int curPage, int pageSize);

    void noticeDelete(Long id);

    List<Notice> findAll();

    Notice noticeView(Long id);

    Notice noticeEdit(Long id);

    void noticeUpdate(Long id, String title, String content);
}
