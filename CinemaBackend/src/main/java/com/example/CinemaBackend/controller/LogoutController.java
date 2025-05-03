package com.example.CinemaBackend.controller;
import com.example.CinemaBackend.service.TokenBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class LogoutController {
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Loại bỏ "Bearer " khỏi token
            tokenBlacklistService.addTokenToBlacklist(token);
            return ResponseEntity.ok("Đăng xuất thành công");
        }
        return ResponseEntity.badRequest().body("Token không hợp lệ");
    }
}
