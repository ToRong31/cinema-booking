package com.cinema.catalog.repository;

import com.cinema.catalog.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByStatus(Movie.Status status);
    List<Movie> findByGenreContainingIgnoreCase(String genre);
}
