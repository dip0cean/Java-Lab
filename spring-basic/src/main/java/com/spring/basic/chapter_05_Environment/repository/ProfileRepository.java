package com.spring.basic.chapter_05_Environment.repository;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
public class ProfileRepository {

    @PostConstruct
    public void postConstruct() {
        System.out.println();
        log.info("@Bean :: Profile 설정이 \"test\" 일 때, ProfileConfig 에 의해 ProfileRepository 인스턴스를 Bean 으로 등록합니다.");
    }
}
