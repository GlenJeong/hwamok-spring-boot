package com.hwamok.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryDslConfiguration {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean //ioc 컨테이너에 위임을 해줌, 쿼리스트링의 환경 설정을 해줌
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }
}
