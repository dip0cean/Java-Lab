package com.spring.basic.chapter_07_ApplicationEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyEventHandler {

    @EventListener
    @Async
    public void handle(MyEvent event) {
        System.out.println();
        log.info("MyEventHandler Thread :: {}", Thread.currentThread());
        log.info("MyEventHandler getData() :: {}", event.getData());
    }

    @EventListener
    @Async
    public void handle(ContextRefreshedEvent event) {
        System.out.println();
        log.info("MyEventHandler Thread :: {}", Thread.currentThread());
        log.info("MyEventHandler ContextRefreshedEvent");
    }

    @EventListener
    @Async
    public void handle(ContextClosedEvent event) {
        System.out.println();
        log.info("MyEventHandler Thread :: {}", Thread.currentThread());
        log.info("MyEventHandler ContextClosedEvent");
    }
}
