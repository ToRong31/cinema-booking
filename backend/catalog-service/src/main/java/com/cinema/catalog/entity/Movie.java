package com.cinema.catalog.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(length = 2000)
    private String description;
    
    private String director;
    private String cast;
    private Integer duration; // in minutes
    private String genre;
    private String language;
    private LocalDate releaseDate;
    private String posterUrl;
    private String trailerUrl;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    
    public enum Status {
        ACTIVE, INACTIVE, COMING_SOON
    }
    
    public Movie() {}
    
    public Movie(Long id, String title, String description, String director, String cast, Integer duration,
                 String genre, String language, LocalDate releaseDate, String posterUrl, String trailerUrl, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
        this.cast = cast;
        this.duration = duration;
        this.genre = genre;
        this.language = language;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
        this.status = status;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getDirector() {
        return director;
    }
    
    public String getCast() {
        return cast;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    
    public String getPosterUrl() {
        return posterUrl;
    }
    
    public String getTrailerUrl() {
        return trailerUrl;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public void setCast(String cast) {
        this.cast = cast;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    
    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
}
