package com.spring.basic.chapter_12_AOP;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimpleEventService implements EventService {

    @NonNull
    @PerAnnotation
    @Override
    public void createEvent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("SimpleEventService.createEvent() :: 새로운 이벤트를 생성합니다.");
    }

    @PerAnnotation
    @Override
    public void publishEvent() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("SimpleEventService.createEvent() :: 생성된 이벤트를 발행합니다.");
    }

    @Override
    public void deleteEvent() {
        log.info("SimpleEventService.deleteEvent() :: 발행된 이벤트를 삭제합니다.");
    }
}
