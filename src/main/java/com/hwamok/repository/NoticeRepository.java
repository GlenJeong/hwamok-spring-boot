package com.hwamok.repository;

import com.hwamok.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Page<Notice> findByTitleContains(String keyword, Pageable pageable);
    Page<Notice> findAllByOrderByIdDesc(PageRequest pageRequest);

}
