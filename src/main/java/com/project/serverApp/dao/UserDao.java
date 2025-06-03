package com.project.serverApp.dao;


import com.project.serverApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user Where login = :login",nativeQuery = true)
    Optional<User> findUserByLogin(@Param("login") String login);

    @Query(value = "SELECT * FROM user Where login = :login and password = :password",nativeQuery = true)
    Optional<User> findUserByLogPas(@Param("login") String login, @Param("password") String password);
}
