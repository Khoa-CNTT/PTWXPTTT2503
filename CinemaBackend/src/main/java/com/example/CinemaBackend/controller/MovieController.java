package com.example.CinemaBackend.controller;

import com.example.CinemaBackend.entity.Movie;
import com.example.CinemaBackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer releaseYear) {
        List<Movie> movies = movieService.searchMovies(title, genre, releaseYear);
        if (movies.isEmpty()) {
            return ResponseEntity.ok().body(List.of()); // Trả về danh sách rỗng nếu không tìm thấy
        }
        return ResponseEntity.ok(movies);
    }
}
