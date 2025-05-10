package com.example.CinemaBackend.controller;

import com.example.CinemaBackend.entity.User;
import com.example.CinemaBackend.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{username}")
    public ResponseEntity<User> getProfile(@PathVariable String username) {
        return ResponseEntity.ok(userProfileService.getUserByUsername(username));
    }

    @PutMapping("/{username}/email")
    public ResponseEntity<User> updateEmail(@PathVariable String username, @RequestParam String email) {
        return ResponseEntity.ok(userProfileService.updateEmail(username, email));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userProfileService.deleteUser(username);
        return ResponseEntity.ok("User deleted successfully");
    }
}
