package com.cinema.catalog.service;

import com.cinema.catalog.entity.Movie;
import com.cinema.catalog.repository.MovieRepository;
import com.cinema.common.web.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    
    private final MovieRepository movieRepository;
    
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    public List<Movie> getActiveMovies() {
        return movieRepository.findByStatus(Movie.Status.ACTIVE);
    }
    
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
    }
    
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    
    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movie = getMovieById(id);
        
        movie.setTitle(movieDetails.getTitle());
        movie.setDescription(movieDetails.getDescription());
        movie.setDirector(movieDetails.getDirector());
        movie.setCast(movieDetails.getCast());
        movie.setDuration(movieDetails.getDuration());
        movie.setGenre(movieDetails.getGenre());
        movie.setLanguage(movieDetails.getLanguage());
        movie.setReleaseDate(movieDetails.getReleaseDate());
        movie.setPosterUrl(movieDetails.getPosterUrl());
        movie.setTrailerUrl(movieDetails.getTrailerUrl());
        movie.setStatus(movieDetails.getStatus());
        
        return movieRepository.save(movie);
    }
    
    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        movieRepository.delete(movie);
    }
}
