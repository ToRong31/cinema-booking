package com.cinema.catalog.controller;

import com.cinema.catalog.entity.Movie;
import com.cinema.catalog.service.MovieService;
import com.cinema.common.web.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    
    private final MovieService movieService;
    
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Movie>>> getAllMovies(@RequestParam(required = false) String status) {
        List<Movie> movies = status != null && status.equals("active") 
            ? movieService.getActiveMovies() 
            : movieService.getAllMovies();
        return ResponseEntity.ok(ApiResponse.success(movies));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Movie>> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(ApiResponse.success(movie));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<Movie>> createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Movie created successfully", createdMovie));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Movie>> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.updateMovie(id, movie);
        return ResponseEntity.ok(ApiResponse.success("Movie updated successfully", updatedMovie));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(ApiResponse.success("Movie deleted successfully", null));
    }
}
