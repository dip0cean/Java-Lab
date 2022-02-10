package com.spring.boot.util.formatter;

import com.spring.boot.domain.Event;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class EventFormatter implements Formatter<Event> {

    @Override
    public Event parse(String id, Locale locale) throws ParseException {
        Event event = new Event();
        event.setId(id);
        return event;
    }

    @Override
    public String print(Event event, Locale locale) {
        return event.getId() + "";
    }
}
