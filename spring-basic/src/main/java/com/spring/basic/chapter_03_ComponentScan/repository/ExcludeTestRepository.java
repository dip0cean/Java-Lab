package com.spring.basic.chapter_03_ComponentScan.repository;

import com.spring.basic.chapter_03_ComponentScan.annotaion.ExcludeTest;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
@ExcludeTest
public class ExcludeTestRepository {
    @PostConstruct
    public void postConstruct() {
        log.info("@IncludeTest :: 해당 객체는 IoC Container 에 등록되지 않습니다. {}", this);
    }
}
