package com.project.serverApp.service;

import com.project.serverApp.domain.CreateEventRequest;
import com.project.serverApp.domain.Event;
import com.project.serverApp.dto.EventDto;

import java.util.List;

public interface EventService {
    Event add(Event event);
    Event updateEvent(Long id, Event event);

    List<Event> getAll();
    Event getById(Long id);

    EventDto getByIdDto(Long id);
    List<EventDto> getAllDto();

    Integer createEvent(CreateEventRequest request);

}
