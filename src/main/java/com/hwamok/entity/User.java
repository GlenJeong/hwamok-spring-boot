package com.hwamok.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hwamok.service.util.CheckBirthday;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

@Entity
public class User extends BaseTimeEntity{
  // DB 예약어 == 특정 단어는 DB에서 사용되니까 테이블이름으로 사용하지마


  // DDD => Domain Driven Design
  // POJO 스프링 특징 중 하나
  // Plain Old Java Object
  // 프레임워크(스프링)에서 독립적으로 움직이는 자바 객체

  // POJO의 대표적인 사용예시
  // VO => Value Object => 동등성을 비교하고 불변하는 (Equals and Hash가 구현) 객체, 비지니스로직 가질수있고 게터세터 가질 수 있음
  // DTO => Data Transfer Object ==> 데이터를 운반 하는 그릇, 비지니스로직을 가질 수 없음, 게터세터를 가질 수 있음
  // Entity(도메인) => 순수한(어디에도 의존적이지 않음) 자바 객체, 데이터베이스와 일대일로 매핑되는 클래스, 비지니스로직 가질 수 있음, 게터 세터 가질 수 있음

  // 비지니스로직 => 뭔가 있어보임 ==> 유효성 검사, 값을 저장하거나 수정하는 가공 , 데이터를 개발자가 가공하고 검증

  // postgresql nextval(시퀀스)
  // oracle nextval(시퀀스)

  // mysql => autoincrement

  // JPA는 ORM Object Relational Mapping
  // 어떤 DB를 쓰더라도 엔티티의 코드가 변경되면 안됨

  // Entity의 멤버변수만 == DB의 컬럼으로 매핑
  // method는 인식x
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 50)
  private String name;

  private String email;

  private String password;

//  @DateTimeFormat(pattern = "yyyy-MM-dd")
//  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")

  @JsonFormat
  private String birthday;

  private String originalName;

  private String saveName;

  private String filePath;

  protected User() {}

  public User(String name, String email, String password, String birthday) throws Exception {
    this.name = name;
    this.email = email;
    this.password = password;
    this.birthday= birthday;
    this.originalName="";
    this.saveName="";
    this.filePath="";
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getBirthday() {
    return birthday;
  }

  public String getOriginalName() {
    return originalName;
  }

  public String getSaveName() {
    return saveName;
  }

  public String getFilePath() {
    return filePath;
  }

  public void changeName(String name) {
    this.name=name;
  }

  public void changePassword(String password) {
    this.password=password;
  }

  public void changeBirthday(String birthday){
    this.birthday=birthday;
  }

  public void uploadFileName(String saveName) {
    this.saveName=saveName;
  }

  public void uploadOrignalName(String originalName) {
    this.originalName=originalName;
  }

  public void uploadFilePath(String filePath) {
    this.filePath=filePath;
  }

  public void updatePassword(String password) {this.password=password;}
}
