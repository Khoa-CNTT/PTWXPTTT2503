package com.example.CinemaBackend.controller;
import com.example.CinemaBackend.entity.WatchHistory;
import com.example.CinemaBackend.service.WatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watch-history")
public class WatchHistoryController {
    @Autowired
    private WatchHistoryService watchHistoryService;

    // Lấy lịch sử xem phim của user
    @GetMapping("/{username}")
    public ResponseEntity<List<WatchHistory>> getWatchHistory(@PathVariable String username) {
        List<WatchHistory> history = watchHistoryService.getWatchHistoryByUsername(username);
        return ResponseEntity.ok(history);
    }

    // Thêm lịch sử xem phim (gọi khi user xem phim)
    @PostMapping("/add")
    public ResponseEntity<WatchHistory> addWatchHistory(@RequestParam String username, @RequestParam Long movieId) {
        WatchHistory history = watchHistoryService.addWatchHistory(username, movieId);
        return ResponseEntity.ok(history);
    }
}
