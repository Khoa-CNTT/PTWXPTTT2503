package com.example.CinemaBackend.service;

import com.example.CinemaBackend.entity.Subscription;
import com.example.CinemaBackend.entity.User;
import com.example.CinemaBackend.repository.SubscriptionRepository;
import com.example.CinemaBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    public Subscription registerPremium(String username, Double price, int days) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Nếu đã có gói, cập nhật lại thời gian, nếu chưa thì tạo mới
        Optional<Subscription> existing = subscriptionRepository.findByUser(user);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endDate = now.plusDays(days);

        Subscription subscription = existing.orElse(new Subscription());
        subscription.setUser(user);
        subscription.setPlanName(Subscription.PlanName.PREMIUM);
        subscription.setPrice(price);
        subscription.setStartDate(now);
        subscription.setEndDate(endDate);

        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> getSubscription(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return subscriptionRepository.findByUser(user);
    }
}
