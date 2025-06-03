package com.project.serverApp.service;


import com.project.serverApp.domain.User;
import com.project.serverApp.dto.EventDto;
import com.project.serverApp.dto.UserDto;

import java.util.List;

public interface UserService {
    User add(User user);
    User update(Long id,User user);
    void deleteById(Long id);

    List<User> getAll();
    User getById(Long id);
    User getByLogin(String login);
    User getByLogPas(String login, String password);

    List<UserDto> getAllDto();
    UserDto getByIdDto(Long id);
    UserDto getByLoginDto(String login);
    UserDto getByLogPasDto(String login, String password);
    List<EventDto> getEventsByIdDto(Long id);
    List<EventDto> getOwnedEventsByIdDto(Long id);
    List<EventDto> getAllSuitableEventsDto(Long id);

}
