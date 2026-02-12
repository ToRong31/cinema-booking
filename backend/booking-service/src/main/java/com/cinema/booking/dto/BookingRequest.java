package com.cinema.booking.dto;

public class BookingRequest {
    private Long userId;
    private Long showtimeId;
    private Integer numberOfSeats;
    private String seatNumbers;
    
    public Long getUserId() { return userId; }
    public Long getShowtimeId() { return showtimeId; }
    public Integer getNumberOfSeats() { return numberOfSeats; }
    public String getSeatNumbers() { return seatNumbers; }
}
