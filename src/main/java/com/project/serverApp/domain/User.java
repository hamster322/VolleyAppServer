package com.project.serverApp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_event_ids",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "event_id")
    private List<Long> events_ids = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_owned_event_ids",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "event_id")
    private List<Long> owned_events_ids = new ArrayList<>();

    public void addEvent(Event event){
        events_ids.add(event.getId());
        event.getUsers_ids().add(id);
    }

}
