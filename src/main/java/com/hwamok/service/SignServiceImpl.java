package com.hwamok.service;

import com.hwamok.controller.dto.UserCreateDTO;
import com.hwamok.entity.User;
import com.hwamok.repository.SignRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


  @Override
  public User signIn(String email, String password) {
    System.out.println("Service email = " + email);
    System.out.println("Service password = " + password);


    //이메일
    
    User user = signRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Not Found User"));
    System.out.println("Service user.getPassword() = " + user.getPassword());
    if(!password.equals(user.getPassword())){
      //throw new RuntimeException("Password Not Match");
    }

    // 비지니스 로직을 처리하는 곳
    // 비밀번호 검증해야 하는 곳 <-- 비지니스 로직

    
    
    // Optional의 의미는 null or not null 2가지만 가지고 있음
    // Optional뒤에 다음에 메서드 체이닝으로 orElseThrow가 null이면 throw한다. (Exception)
    // orElseThrow는 람다식을 통해서 제가 익셉션을 지정할 수 있음
    // 람다식 쓰는 방법
    // 람다식 기본 형태는 () -> {}
    // 파라미터가 1개 있을 땐 param -> {}
    // 파라미터가 2개이상 (param1, param2) -> {}
    // {}는 구현부인테 한 줄 일때는 {} 생략 가능
    //

    return user;


  }

  @Override
  public String emailCheck(String email) {
    Optional<User> byUserEmail = signRepository.findByEmail(email);

    if(byUserEmail.isPresent()){
      return null;
    }else{
      return "ok";
    }
  }

}
