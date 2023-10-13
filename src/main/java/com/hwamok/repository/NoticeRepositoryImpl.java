package com.hwamok.repository;

import com.hwamok.entity.Notice;
import com.hwamok.entity.QNotice;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;

public class NoticeRepositoryImpl implements NoticeRepositoryCustom {
    private JPAQueryFactory jpaQueryFactory;


    // QClass = proxy 객체 - querydsl의 거지 같은 점이 나옴
    // QClass가 없으면 querydsl 사용이 불가능함
    // QClass가 가끔 안 만들어질 때가 있음 Gradle 에서 tasks -> clean -> bulid 하면 QClass가 생김

    private static final QNotice notice = QNotice.notice;


    public NoticeRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public Page<Notice> getNotices(String keyword, Pageable pageable) {
        // 조건을 구현하는 2가지 방법
        // BooleanBuilder O, 보통 이걸 많이 사용
        // Where 직접 구현은 조건이 많으면 가독성이 떨이지기 때문에 잘 안 씀 X
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        // 메서드로 booleanbuilder 구현
        // where에서 and ==> and
        // or => or

        // querydsl, jpa = N + 1문제? 다음 시간에 설명해줌
        // 데이터를 조회할 때 1개의 쿼리로 요청이 처리 될 것으로 기대했으나
        // 의도하지 않은 N개의 쿼리가 추가적으로 더 발생하는 현상

        booleanBuilder.and(eqKeyword(keyword));
        return new PageImpl<>(
                jpaQueryFactory
                        .selectFrom(notice) // SELECT * FROM NOTICE
                        .where(booleanBuilder)
                        .orderBy(notice.id.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .stream().collect(Collectors.toList()),
                pageable,
                jpaQueryFactory.select(notice.id).from(notice).where(booleanBuilder).stream().count()
        );
    }
    // 이 클래스안에서만 사용하기 때문에 private 사용
    // 네이밍하는 방법이 where email = ? and name = ?
    // email이 null이면 where name = ?
    // name이 null이면 where email = ?
    // 어떤 값이 들어오냐에 따라 조건이 절이 달라지는 걸 다이나믹 쿼리라고 한다. => 동적쿼리
    // 동적 쿼리는 보통 불리언 빌더로 작성 = > 메서드로 구현 => eq{parameterName}()

    private BooleanExpression eqKeyword(String keyword) { // 타입은 BooleanExpression 사용
        // keyword가 널이거나 빈 값이면 return null,
        // keyword가 있다면 title like하면 되고
        // content에서도 like
        if(Strings.isBlank(keyword)){ // keyword가 널이면
            return null;
        }

        return notice.title.contains(keyword).or(notice.content.contains(keyword));
        //
    }
}
