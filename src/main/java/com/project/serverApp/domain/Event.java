package com.project.serverApp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private Long owner_id;

    @ElementCollection
    @CollectionTable(name = "event_users_ids",joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "user_id")
    private List<Long> users_ids = new ArrayList<>();

    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "place")
    private String place;

}
