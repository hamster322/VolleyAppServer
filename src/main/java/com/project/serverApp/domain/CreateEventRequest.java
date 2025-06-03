package com.project.serverApp.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateEventRequest {
    private long owner_id;
    private String place;
    private long dateTime;
}
