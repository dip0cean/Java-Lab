package com.spring.basic.chapter_05_Environment.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Slf4j
@Repository
@Profile("!test")
public class BeanRepository {
    @PostConstruct
    public void postConstruct() {
        System.out.println();
        log.info("@Repository :: Profile 설정이 \"test\" 가 아닐 때, @ComponentScan 에 의해 자동으로 Spring IoC Container 에 등록된다.");
    }
}
