package com.cinema.showtime.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "showtimes")
public class Showtime {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long movieId;
    
    @Column(nullable = false)
    private String screenName;
    
    @Column(nullable = false)
    private LocalDateTime startTime;
    
    @Column(nullable = false)
    private LocalDateTime endTime;
    
    @Column(nullable = false)
    private Double price;
    
    private Integer totalSeats = 100;
    private Integer availableSeats = 100;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;
    
    public enum Status {
        SCHEDULED, ONGOING, COMPLETED, CANCELLED
    }
    
    public Showtime() {}
    
    public Showtime(Long id, Long movieId, String screenName, LocalDateTime startTime, LocalDateTime endTime, 
                    Double price, Integer totalSeats, Integer availableSeats, Status status) {
        this.id = id;
        this.movieId = movieId;
        this.screenName = screenName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.status = status;
    }
    
    public Long getId() { return id; }
    public Long getMovieId() { return movieId; }
    public String getScreenName() { return screenName; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public Double getPrice() { return price; }
    public Integer getTotalSeats() { return totalSeats; }
    public Integer getAvailableSeats() { return availableSeats; }
    public Status getStatus() { return status; }
    
    public void setId(Long id) { this.id = id; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public void setScreenName(String screenName) { this.screenName = screenName; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public void setPrice(Double price) { this.price = price; }
    public void setTotalSeats(Integer totalSeats) { this.totalSeats = totalSeats; }
    public void setAvailableSeats(Integer availableSeats) { this.availableSeats = availableSeats; }
    public void setStatus(Status status) { this.status = status; }
}
