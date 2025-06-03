package com.project.serverApp.service.Impl;

import com.project.serverApp.dao.EventDao;
import com.project.serverApp.dao.UserDao;
import com.project.serverApp.domain.Event;
import com.project.serverApp.domain.User;
import com.project.serverApp.dto.EventDto;
import com.project.serverApp.dto.UserDto;
import com.project.serverApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final EventDao eventDao;

    @Override
    public User add(User user) {
        return userDao.save(user);
    }

    @Override
    public User update(Long id, User user) {
        Optional<User> userOptional = userDao.findById(id);
        if (!userOptional.isPresent()) throw new RuntimeException("No user with this Id");

        User updateUser = userOptional.get();
        updateUser.setLogin(user.getLogin());
        updateUser.setPassword(user.getPassword());
        return userDao.save(updateUser);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(Long id) {
        Optional<User> userOptional = userDao.findById(id);
        if (!userOptional.isPresent()) return new User(0l,"","",null,null);
        return userOptional.get();
    }

    @Override
    public User getByLogin(String login) {
        Optional<User> userOptional = userDao.findUserByLogin(login);
        if (!userOptional.isPresent()) return new User(0l,"","",null,null);
        return userOptional.get();
    }

    @Override
    public User getByLogPas(String login, String password) {
        Optional<User> userOptional = userDao.findUserByLogPas(login,password);
        if (!userOptional.isPresent()) return new User(0l,"","",null,null);
        return userOptional.get();
    }


    @Override
    public List<UserDto> getAllDto() {
        List<UserDto> dtos = new ArrayList<>();
        List<User> users = getAll();
        for (User user: users){
            dtos.add(UserToDto(user));
        }
        return dtos;
    }

    @Override
    public UserDto getByIdDto(Long id) {
        return UserToDto(getById(id));
    }

    @Override
    public UserDto getByLoginDto(String login) {
        return UserToDto(getByLogin(login));
    }

    @Override
    public UserDto getByLogPasDto(String login, String password) {
        return UserToDto(getByLogPas(login,password));
    }

    @Override
    public List<EventDto> getEventsByIdDto(Long id) {
        User user = userDao.getById(id);
        List<EventDto> dtos = new ArrayList<>();
        for (Long id_event: user.getEvents_ids()){
            dtos.add(EventToDto(eventDao.getById(id_event)));
        }
        return dtos;
    }

    @Override
    public List<EventDto> getOwnedEventsByIdDto(Long id) {
        User user = userDao.getById(id);
        List<EventDto> dtos = new ArrayList<>();
        for (Long id_event: user.getOwned_events_ids()){
            dtos.add(EventToDto(eventDao.getById(id_event)));
        }
        return dtos;
    }

    @Override
    public List<EventDto> getAllSuitableEventsDto(Long id) {
        User user = userDao.getById(id);
        List<Event> events = eventDao.findAll();
        List<EventDto> dtos = new ArrayList<>();
        for(Event event: events){
            if ((!user.getEvents_ids().contains(event.getId()))&&(!event.getOwner_id().equals(user.getId()))){
                dtos.add(EventToDto(event));
            }
        }
        return dtos;
    }

    UserDto UserToDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        return dto;
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
}
