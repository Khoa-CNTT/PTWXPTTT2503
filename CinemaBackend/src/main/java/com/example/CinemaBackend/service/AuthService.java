package com.example.CinemaBackend.service;

import com.example.CinemaBackend.dto.LoginRequest;
import com.example.CinemaBackend.dto.LoginResponse;
import com.example.CinemaBackend.entity.User;
import com.example.CinemaBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Sai tên người dùng hoặc mật khẩu");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Sai tên người dùng hoặc mật khẩu");
        }

        // Tạo token giả (thay bằng JWT trong thực tế)
        String token = "dummy-token-for-" + user.getUsername();

        LoginResponse response = new LoginResponse();
        response.setMessage("Đăng nhập thành công");
        response.setToken(token);
        response.setRole(user.getRole().name());
        return response;
    }
}
