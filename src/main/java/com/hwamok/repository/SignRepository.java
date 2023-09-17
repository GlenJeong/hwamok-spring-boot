package com.hwamok.repository;

import com.hwamok.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignRepository extends JpaRepository<User, Long> {
  // T (parameter generic) --> Entity를 파라미터로써 전달
  // E (entity generic)
}
