package com.project.serverApp.controller;


import com.project.serverApp.domain.User;
import com.project.serverApp.dto.EventDto;
import com.project.serverApp.dto.UserDto;
import com.project.serverApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/PostUser")
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @GetMapping("/GetAllUsers")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/GetAllUsersDto")
    public List<UserDto> getAllDto() {return userService.getAllDto();}

    @GetMapping("/GetUser/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("GetUserDto/{id}")
    public UserDto getByIdDto(@PathVariable Long id) {return userService.getByIdDto(id);}

    @PutMapping("/UpdateUser/{id}")
    public User update(@PathVariable long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/DeleteUser/{id}")
    public void deleteById(@PathVariable long id) {
        userService.deleteById(id);
    }

    @GetMapping("/GetUserByLogin/{login}")
    public User getByLogin(@PathVariable String login) {
        return userService.getByLogin(login);
    }

    @GetMapping("/GetUserByLogPas/{login}/{password}")
    public User getUserByLogPas(@PathVariable(name = "login") String login, @PathVariable(name = "password") String password) {
        return userService.getByLogPas(login, password);
    }
    @GetMapping("/GetUserEventsById/{id}")
    public List<EventDto> getUserEventsById(@PathVariable(name = "id")Long id){
        return userService.getEventsByIdDto(id);
    }
    @GetMapping("/GetUserOwnedEventsById/{id}")
    public List<EventDto> getUserOwnedEventsById(@PathVariable(name = "id")Long id){
        return userService.getOwnedEventsByIdDto(id);
    }
    @GetMapping("/GetUserAvailableEvents/{id}")
    public List<EventDto> getUserAvailableEvents(@PathVariable(name = "id")Long id){
        return userService.getAllSuitableEventsDto(id);
    }
}
