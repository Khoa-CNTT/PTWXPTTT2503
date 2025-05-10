package com.example.CinemaBackend.service;
import com.example.CinemaBackend.entity.User;
import com.example.CinemaBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateEmail(String username, String newEmail) {
        User user = getUserByUsername(username);
        user.setEmail(newEmail);
        return userRepository.save(user);
    }

    public void deleteUser(String username) {
        User user = getUserByUsername(username);
        userRepository.delete(user);
    }
}
