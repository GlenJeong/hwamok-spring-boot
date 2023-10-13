package com.hwamok.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@MappedSuperclass //해당 어노테이션은 객체의 입장에서 '생성 시간', '수정 시간' 같은 공통 매핑 정보가 필요할 때 사용
@EntityListeners(AuditingEntityListener.class)
// EntityListeners란 JPA Entity에서 이벤트가 발생할 때마다 특정 로직을 실행시킬 수 있는 어노테이션
// AuditingEntityListener class
// 대상 개체에 업데이트 이벤트가 일어나는 것을 auditing 하도록 설정된 경우에 생성된 날짜, 수정된 날짜,
// 생성자를 저장할 수 있도록 활성화한다.
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate;
    //@CreatedDate, @LastModifiedDate를 사용하여 생성된 시간 정보, 수정된 시간 정보를 자동으로 저장할 수 있다.
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
// 많은 테이블에서 공통적으로 사용될 컬럼인 생성된 시간과 수정된 시간을 필드로 가진 BaseTime Class
// 대부분의 테이블에서 공통적으로 사용될 컬럼이니만큼 각각의 Entity에 생성하는 것보다
// 부모 클래스로 만들어 상속받아서 사용하는 것이 효율적이라 그렇게 많이 사용되고 있습니다.