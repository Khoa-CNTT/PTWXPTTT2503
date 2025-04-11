package com.example.CinemaBackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
    
    private static final String POSTER_DIR = "uploads/posters/";
    private static final String VIDEO_DIR = "uploads/videos/";

    @PostMapping("/upload-poster")
    public ResponseEntity<String> uploadPoster(@RequestParam("file") MultipartFile file) {
        return uploadFile(file, POSTER_DIR);
    }

    @PostMapping("/upload-video")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        return uploadFile(file, VIDEO_DIR);
    }

    private ResponseEntity<String> uploadFile(MultipartFile file, String directory) {
        try {
            // Tạo thư mục nếu chưa tồn tại
            Path dirPath = Paths.get(directory);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Lưu file vào thư mục
            String filePath = directory + file.getOriginalFilename();
            File dest = new File(filePath);
            file.transferTo(dest);

            // Trả về URL của file
            String fileUrl = "http://localhost:8080/" + filePath;
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }
}
