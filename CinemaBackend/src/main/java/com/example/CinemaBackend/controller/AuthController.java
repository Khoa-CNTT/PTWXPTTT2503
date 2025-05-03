package com.example.CinemaBackend.controller;

import com.example.CinemaBackend.dto.LoginRequest;
import com.example.CinemaBackend.dto.LoginResponse;
import com.example.CinemaBackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CinemaBackend.dto.ChangePasswordRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Sử dụng constructor không tham số và setter
            LoginResponse errorResponse = new LoginResponse();
            errorResponse.setMessage(e.getMessage());
            errorResponse.setToken(null);
            errorResponse.setRole(null);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            authService.changePassword(request);
            return ResponseEntity.ok("Đổi mật khẩu thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
