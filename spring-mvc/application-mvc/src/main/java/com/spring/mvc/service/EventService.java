package com.spring.mvc.service;

import com.spring.mvc.model.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EventService {

    public List<Event> getEvents() {
        Event event_1 = Event
                .builder()
                .name("Miracle Morning! Day 1")
                .limitOfEnrollment(1)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusHours(3))
                .build();

        Event event_2 = Event
                .builder()
                .name("Miracle Morning! Day 2")
                .limitOfEnrollment(1)
                .startDateTime(LocalDateTime.now().plusDays(1))
                .endDateTime(LocalDateTime.now().plusDays(1).plusHours(3))
                .build();

        return Arrays.asList(event_1, event_2);
    }
}
