package com.spring.basic.chapter_10_Data_Binding.ConversionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConversionServiceRunner implements ApplicationRunner {

    private final ConversionService conversionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ConversionService 인터페이스의 구현체 :: {}", conversionService.getClass());
        log.info("ConversionService 에 등록된 Converter / Formatter 객체 :: {}", conversionService);
    }
}
