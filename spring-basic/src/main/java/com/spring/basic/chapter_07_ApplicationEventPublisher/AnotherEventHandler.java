package com.spring.basic.chapter_07_ApplicationEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnotherEventHandler {

    @EventListener
    @Async
    public void handle(MyEvent event) {
        System.out.println();
        log.info("AnotherEventHandler Thread :: {}", Thread.currentThread());
        log.info("AnotherEventHandler getData() :: {}", event.getData());
    }
}
