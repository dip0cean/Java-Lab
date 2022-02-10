package com.spring.boot.util.converter;

import com.spring.boot.domain.Event;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventConverter implements Converter<String, Event> {

    @Override
    public Event convert(String id) {
        Event event = new Event();
        event.setId(id);
        return event;
    }
}
