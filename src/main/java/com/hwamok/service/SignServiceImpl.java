package com.hwamok.service;

import com.hwamok.controller.SignController;
import com.hwamok.controller.dto.UserCreateDTO;
import com.hwamok.entity.User;
import com.hwamok.repository.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {
  // 이렇게 하는건 관례 에요.
  // 클래스만들때 대문자 시작
  // 메소드만들땐 소문자 시작 대문자로 이어감
  // 마지막줄은 공백이다. (컴파일러가 마지막줄이 공백이아니면 에러를 뱉음)


  // 연결한다 ==> 의존성 주입
  // 의존하다 => a 는 b에 의존적이다 => b가 변하면 a도 변한다.
  // 필드 주입 (사용을 권장하지 않음) -> 순환참조 에대한 대처가 되지 않음
  // 사용하는 곳이 한군데 있음 -> 테스트 코드

  // a -> b 참조 중임 b -> a 참조
  // b가 변하면 a도 변함 a가 변하면 b도 변함
  // @Autowired
  // @Inject

  // 생성자주입 (권장)

  private SignRepository signRepository;

  public SignServiceImpl(SignRepository signRepository) {
    this.signRepository = signRepository;
  }

  @Override
  public void signUp(UserCreateDTO dto) {
    signRepository.save(new User(dto.getName(), dto.getEmail(), dto.getPassword()));
  }
}
