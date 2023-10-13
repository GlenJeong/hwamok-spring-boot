package com.hwamok.repository;

import com.hwamok.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {
    Page<Notice> getNotices(String keyword, Pageable pageable);
}
