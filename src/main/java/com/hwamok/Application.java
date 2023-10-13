package com.hwamok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//어플리케이션의 main method가 있는 클래스에 적용하며 JPA Auditing(감시, 감사) 기
// 능을 활성화하기 위한 어노테이션이다. 해당 어노테이션을 적용함으로써 createdDate,
// modifiedDate처럼 DB에 데이터가 저장되거나 수정될 때 언제, 누가 했는지를 자동으로 관리할 수 있게 된다.
@EnableJpaAuditing
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
