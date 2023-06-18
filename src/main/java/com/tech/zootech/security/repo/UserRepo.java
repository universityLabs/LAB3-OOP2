package com.tech.zootech.security.repo;


import com.tech.zootech.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles")
    List<User> findAllUsers();

    @Query("select u from User u LEFT JOIN FETCH u.roles where u.username = :username")
    User findByUsername(String username);

    @Query("select u from User u LEFT JOIN FETCH u.roles where u.name = :name")
    Optional<List<User>> findByName(String name);

    @Query("select u from User u LEFT JOIN FETCH u.roles where u.password = :password")
    Optional<List<User>> findUserByPassword(String password);

    @Query("select u from User u LEFT JOIN FETCH u.roles where u.created between :start and :end")
    Optional<User> findByCreatedBetween(LocalDateTime start, LocalDateTime end);
}
