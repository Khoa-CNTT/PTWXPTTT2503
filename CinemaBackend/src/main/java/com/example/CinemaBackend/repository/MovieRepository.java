package com.example.CinemaBackend.repository;
import com.example.CinemaBackend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE " +
           "(:title IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
           "(:genre IS NULL OR LOWER(m.genre) LIKE LOWER(CONCAT('%', :genre, '%'))) AND " +
           "(:releaseYear IS NULL OR m.releaseYear = :releaseYear)")
    List<Movie> searchMovies(@Param("title") String title,
                             @Param("genre") String genre,
                             @Param("releaseYear") Integer releaseYear);
}
