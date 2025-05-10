package com.example.CinemaBackend.controller;

import com.example.CinemaBackend.entity.Subscription;
import com.example.CinemaBackend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    // Đăng ký gói trả phí
    @PostMapping("/register")
    public ResponseEntity<Subscription> registerPremium(
            @RequestParam String username,
            @RequestParam Double price,
            @RequestParam(defaultValue = "30") int days) {
        Subscription subscription = subscriptionService.registerPremium(username, price, days);
        return ResponseEntity.ok(subscription);
    }

    // Xem thông tin gói đã đăng ký
    @GetMapping("/{username}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable String username) {
        return subscriptionService.getSubscription(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
