package com.spring.basic.chapter_07_ApplicationEventPublisher;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyEvent {
    private int data;
    private Object source;
}
