package com.project.serverApp.dao;


import com.project.serverApp.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {
}
