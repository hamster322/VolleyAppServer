package com.project.serverApp.controller;

import com.project.serverApp.domain.CreateEventRequest;
import com.project.serverApp.domain.Event;
import com.project.serverApp.domain.User;
import com.project.serverApp.dto.EventDto;
import com.project.serverApp.service.EventService;
import com.project.serverApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {
    public final EventService eventService;
    public final UserService userService;

    @GetMapping("/Events")
    public List<Event> getAll(){
        return eventService.getAll();
    }

    @GetMapping("/EventsDto")
    public List<EventDto> getAllDto(){return eventService.getAllDto();}

    @PostMapping("/Events")
    public Integer createEvent(@RequestBody CreateEventRequest request){
        return eventService.createEvent(request);
    }

    @GetMapping("/Events/AddParticipant/{id_user}/{id_event}")
    public Integer addUserToEvent(@PathVariable(name = "id_user") Long id_user, @PathVariable(name = "id_event") Long id_event){
        Event event = eventService.getById(id_event);

        User user = userService.getById(id_user);

        user.addEvent(event);

        eventService.updateEvent(id_event,event);
        userService.update(id_user,user);

        return 1;
    }
}
