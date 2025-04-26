package com.example.CinemaBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Constructor không tham số
@AllArgsConstructor // Constructor với tất cả tham số
public class LoginResponse {
    private String message;
    private String token; // Token sẽ được sử dụng để xác thực
    private String role;  // Vai trò của người dùng (ADMIN/USER)
    

    /**
     * @return String return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
     public String getToken() {
        return token;
     }

     public void setToken(String token) {
        this.token = token;
     }

     public String getRole() {
        return role;
     }
     public void setRole(String role) {
        this.role = role;
    }
        
}
