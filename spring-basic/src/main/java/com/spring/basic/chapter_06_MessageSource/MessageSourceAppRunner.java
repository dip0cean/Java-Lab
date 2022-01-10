package com.spring.basic.chapter_06_MessageSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSourceAppRunner implements ApplicationRunner {

    private final MessageSource messageSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println();
        log.info("Locale.getDefault() :: {}", Locale.getDefault());
        log.info("영어 : {}", messageSource.getMessage("hello", new String[]{"World!"}, Locale.getDefault()));
        log.info("한국어 : {}", messageSource.getMessage("hello", new String[]{"세상아!"}, Locale.KOREA));

        // ReloadableMessageSource
//        while(true) {
//            log.info("영어 : {}", messageSource.getMessage("hello", new String[]{"World!"}, Locale.getDefault()));
//            log.info("한국어 : {}", messageSource.getMessage("hello", new String[]{"세상아!"}, Locale.KOREA));
//            Thread.sleep(1000L);
//        }
    }
}
