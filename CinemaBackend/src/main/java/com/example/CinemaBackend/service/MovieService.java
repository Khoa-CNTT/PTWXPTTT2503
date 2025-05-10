package com.example.CinemaBackend.service;

import com.example.CinemaBackend.entity.Movie;
import com.example.CinemaBackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> searchMovies(String title, String genre, Integer releaseYear) {
        return movieRepository.searchMovies(title, genre, releaseYear);
    }
}
