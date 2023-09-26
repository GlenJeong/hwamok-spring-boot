package com.hwamok.repository;

import com.hwamok.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
