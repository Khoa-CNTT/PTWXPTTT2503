package com.example.CinemaBackend.service;

import com.example.CinemaBackend.entity.WatchHistory;
import com.example.CinemaBackend.entity.User;
import com.example.CinemaBackend.entity.Movie;
import com.example.CinemaBackend.repository.WatchHistoryRepository;
import com.example.CinemaBackend.repository.UserRepository;
import com.example.CinemaBackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WatchHistoryService {
    @Autowired
    private WatchHistoryRepository watchHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<WatchHistory> getWatchHistoryByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) throw new RuntimeException("User not found");
        return watchHistoryRepository.findByUser(userOpt.get());
    }

    public WatchHistory addWatchHistory(String username, Long movieId) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if (userOpt.isEmpty() || movieOpt.isEmpty()) throw new RuntimeException("User or Movie not found");

        WatchHistory history = new WatchHistory();
        history.setUser(userOpt.get());
        history.setMovie(movieOpt.get());
        history.setWatchedAt(LocalDateTime.now());
        return watchHistoryRepository.save(history);
    }
}
