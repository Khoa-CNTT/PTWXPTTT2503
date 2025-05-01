package com.example.CinemaBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import  com.example.CinemaBackend.entity.User;
import java.util.Optional;

public interface  UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
