package com.hwamok.repository;

import com.hwamok.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    // 이 메일로 회원 정보 조회(select * from user where email="jyb1624@test.com";)

}
