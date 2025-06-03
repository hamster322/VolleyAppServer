package com.project.serverApp.service.Impl;

import com.project.serverApp.dao.EventDao;
import com.project.serverApp.dao.UserDao;
import com.project.serverApp.domain.CreateEventRequest;
import com.project.serverApp.domain.Event;
import com.project.serverApp.domain.User;
import com.project.serverApp.dto.EventDto;
import com.project.serverApp.dto.UserDto;
import com.project.serverApp.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventDao eventDao;
    private final UserDao userDao;

    @Override
    public List<Event> getAll() {
        return eventDao.findAll();
    }

    @Override
    public Event add(Event event) {
        return eventDao.save(event);
    }

    @Override
    public Event getById(Long id) {
        return eventDao.getById(id);
    }

    @Override
    public EventDto getByIdDto(Long id) {
        Event event = eventDao.getById(id);
        EventDto dto = EventToDto(event);
        return dto;
    }

    @Override
    public List<EventDto> getAllDto() {
        List<Event> events = eventDao.findAll();
        List<EventDto> dtos = new ArrayList<>();
        for (Event event: events){
            dtos.add(EventToDto(event));
        }
        return dtos;
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Event updateEvent = eventDao.getById(id);
        updateEvent.setTime(event.getTime());
        updateEvent.setPlace(event.getPlace());
        updateEvent.setOwner_id(event.getOwner_id());
        updateEvent.setUsers_ids(event.getUsers_ids());

        return eventDao.save(updateEvent);
    }

    @Override
    public Integer createEvent(CreateEventRequest request) {
        User user = userDao.getById(request.getOwner_id());
        Event event = new Event();
        event.setOwner_id(request.getOwner_id());
        event.setTime(Instant.ofEpochMilli(request.getDateTime()).atZone(ZoneId.of("Europe/Moscow")).toLocalDateTime());
        event.setPlace(request.getPlace());

        event.setUsers_ids(new ArrayList<>());

        Event savedEvent = eventDao.save(event);
        user.getOwned_events_ids().add(savedEvent.getId());
        userDao.save(user);
        return 1;
    }

    EventDto EventToDto(Event event){

        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setPlace(event.getPlace());
        dto.setTimeInMilles(event.getTime().atZone(ZoneId.of("Europe/Moscow")).toInstant().toEpochMilli());
        dto.setOwner(UserToDto(userDao.getById(event.getOwner_id())));
        List<UserDto> users = new ArrayList<>();
        for (Long id:event.getUsers_ids()){users.add(UserToDto(userDao.getById(id)));}
        dto.setUsers(users);

        return dto;
    }
    UserDto UserToDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        return dto;
    }


}
