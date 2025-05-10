package com.example.CinemaBackend.repository;
import com.example.CinemaBackend.entity.WatchHistory;
import com.example.CinemaBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
    List<WatchHistory> findByUser(User user);
}
