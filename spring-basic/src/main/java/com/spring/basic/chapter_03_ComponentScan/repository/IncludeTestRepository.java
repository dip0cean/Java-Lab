package com.spring.basic.chapter_03_ComponentScan.repository;

import com.spring.basic.chapter_03_ComponentScan.annotaion.IncludeTest;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
@IncludeTest
public class IncludeTestRepository {
    @PostConstruct
    public void postConstruct() {
        System.out.println();
        log.info("@IncludeTest :: 해당 객체는 IoC Container 에 등록되었습니다. {}", this);
    }
}
