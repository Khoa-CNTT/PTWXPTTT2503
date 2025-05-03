package com.example.CinemaBackend.service;

import com.example.CinemaBackend.dto.LoginRequest;
import com.example.CinemaBackend.dto.LoginResponse;
import com.example.CinemaBackend.dto.ChangePasswordRequest;
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

    public void changePassword(ChangePasswordRequest request) {
        // Lấy thông tin người dùng hiện tại (giả định username được lấy từ SecurityContext)
        String username = "current-logged-in-user"; // Thay bằng logic lấy username từ SecurityContext
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Người dùng không tồn tại");
        }

        User user = userOptional.get();

        // Kiểm tra mật khẩu hiện tại
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu hiện tại không chính xác");
        }

        // Kiểm tra mật khẩu mới không trùng với mật khẩu hiện tại
        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu mới không được trùng với mật khẩu hiện tại");
        }

        // Kiểm tra tiêu chí bảo mật của mật khẩu mới
        if (!isValidPassword(request.getNewPassword())) {
            throw new RuntimeException("Mật khẩu mới không đáp ứng tiêu chí bảo mật");
        }

        // Cập nhật mật khẩu mới
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    private boolean isValidPassword(String password) {
        // Kiểm tra độ dài tối thiểu 8 ký tự, có chữ hoa, chữ thường, số
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*\\d.*");
    }
}
