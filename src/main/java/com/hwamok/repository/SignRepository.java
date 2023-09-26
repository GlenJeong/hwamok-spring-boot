package com.hwamok.repository;

import com.hwamok.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignRepository extends JpaRepository<User, Long> {
  // T (parameter generic) --> Entity를 파라미터로써 전달
  // E (entity generic)

    // email로 조회를 하는 기능을 만들려고함
    // select * form User where User.email = ?
    // select * form User where User.email IN In(?, ?, ?)
    // select * form User where User.email = ?
    //Order by User.id desc, asc

    // JPA에서 쿼리 대신 QueryMethod라는 것을 많이 씀
    // 조회: find
    // 조건이 붙을 때: By(조건컬럼)
    // and를 붙일 때는 And
    // or를 붙일 때는 Or
    // In을 붙일 땐 In(?, ?, ?)
    // Order by ?? desc 붙일 땐 OrderBy(조건컬럼) Desc, Asc

    // 가독성을 해칠 수 있음
    // 조회 성능이 극혐이라 조건이 많이 붙는 조회시에는
    // QuaryDsl(우리가 쓸 것), JOQQ
    // findByEmailAndIdAndNameOrderByIdDesc
    // findByEmail 우리가 찾고자 하는 게 뭘까?
    // User 객체가 필요
    // User가 없을 수도 있지 않을까?
    // 없을 수도 있다 => null일 수 있다.
    // Java에서는 Nullable을 Optional<T>


    Optional<User> findByEmail(String email);

    // void findByEmailAndIdAndName(String email, Long id, String name);


}
