package com.cinema.booking.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private Long showtimeId;
    
    @Column(nullable = false)
    private Integer numberOfSeats;
    
    @Column(nullable = false)
    private Double totalAmount;
    
    private String seatNumbers; // e.g., "A1,A2,A3"
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
    
    private LocalDateTime bookingTime = LocalDateTime.now();
    private LocalDateTime expiryTime;
    
    @Column(unique = true)
    private String bookingReference;
    
    public enum Status {
        PENDING, CONFIRMED, CANCELLED, EXPIRED
    }
    
    public Booking() {}
    
    public Booking(Long id, Long userId, Long showtimeId, Integer numberOfSeats, Double totalAmount,
                   String seatNumbers, Status status, LocalDateTime bookingTime, LocalDateTime expiryTime, String bookingReference) {
        this.id = id;
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.numberOfSeats = numberOfSeats;
        this.totalAmount = totalAmount;
        this.seatNumbers = seatNumbers;
        this.status = status;
        this.bookingTime = bookingTime;
        this.expiryTime = expiryTime;
        this.bookingReference = bookingReference;
    }
    
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getShowtimeId() { return showtimeId; }
    public Integer getNumberOfSeats() { return numberOfSeats; }
    public Double getTotalAmount() { return totalAmount; }
    public String getSeatNumbers() { return seatNumbers; }
    public Status getStatus() { return status; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public LocalDateTime getExpiryTime() { return expiryTime; }
    public String getBookingReference() { return bookingReference; }
    
    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setShowtimeId(Long showtimeId) { this.showtimeId = showtimeId; }
    public void setNumberOfSeats(Integer numberOfSeats) { this.numberOfSeats = numberOfSeats; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public void setSeatNumbers(String seatNumbers) { this.seatNumbers = seatNumbers; }
    public void setStatus(Status status) { this.status = status; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
    public void setExpiryTime(LocalDateTime expiryTime) { this.expiryTime = expiryTime; }
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; }
}
