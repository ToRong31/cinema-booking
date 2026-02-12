package com.cinema.booking.controller;

import com.cinema.booking.dto.BookingRequest;
import com.cinema.booking.entity.Booking;
import com.cinema.booking.service.BookingService;
import com.cinema.common.web.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    
    private final BookingService bookingService;
    
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Booking>>> getAllBookings(
            @RequestParam(required = false) Long userId) {
        List<Booking> bookings = userId != null 
            ? bookingService.getBookingsByUserId(userId)
            : bookingService.getAllBookings();
        return ResponseEntity.ok(ApiResponse.success(bookings));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Booking>> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(ApiResponse.success(booking));
    }
    
    @GetMapping("/reference/{reference}")
    public ResponseEntity<ApiResponse<Booking>> getBookingByReference(@PathVariable String reference) {
        Booking booking = bookingService.getBookingByReference(reference);
        return ResponseEntity.ok(ApiResponse.success(booking));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<Booking>> createBooking(@RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Booking created successfully", booking));
    }
    
    @PutMapping("/{id}/confirm")
    public ResponseEntity<ApiResponse<Booking>> confirmBooking(@PathVariable Long id) {
        Booking booking = bookingService.confirmBooking(id);
        return ResponseEntity.ok(ApiResponse.success("Booking confirmed", booking));
    }
    
    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<Booking>> cancelBooking(@PathVariable Long id) {
        Booking booking = bookingService.cancelBooking(id);
        return ResponseEntity.ok(ApiResponse.success("Booking cancelled", booking));
    }
}
