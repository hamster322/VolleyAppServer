package com.project.serverApp.dto;

import com.project.serverApp.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class EventDto {
    private Long id;
    private UserDto owner;
    private String place;
    private Long timeInMilles;
    private List<UserDto> users;

}
