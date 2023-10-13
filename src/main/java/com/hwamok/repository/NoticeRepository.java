package com.hwamok.repository;

import com.hwamok.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {
    // querydsl은 사용방법이 정해져 있음 - 이름까지 정해져있음
    // 이름 내 맘대로 지으면 동작안함
    // 여기서 모체란 JpaRepository를 상속 받은 자식 인터페이스를 의미하는 인터페이스 NoticeRepository
    // {모체이름}Custom : interface
    // NoticeRepositoryCustom
    // {모체이름}CustomImpl : class
    // NoticeRepositoryCustomImpl 또는 NoticeRepositoryImpl

    // 모체에서는 {모체이름}Custom이 interface를 extends 받아야함

    Page<Notice> findAllByOrderByIdDesc(PageRequest pageRequest);

}
