package com.example.CinemaBackend.repository;

import com.example.CinemaBackend.entity.Subscription;
import com.example.CinemaBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByUser(User user);
}
