package com.cinema.booking.service;

import com.cinema.booking.dto.BookingRequest;
import com.cinema.booking.entity.Booking;
import com.cinema.booking.repository.BookingRepository;
import com.cinema.common.web.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {
    
    private final BookingRepository bookingRepository;
    
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
    
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }
    
    public Booking getBookingByReference(String reference) {
        return bookingRepository.findByBookingReference(reference)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with reference: " + reference));
    }
    
    public Booking createBooking(BookingRequest request) {
        // In real scenario, you would:
        // 1. Check showtime availability
        // 2. Lock the seats temporarily
        // 3. Calculate the total amount
        
        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setShowtimeId(request.getShowtimeId());
        booking.setNumberOfSeats(request.getNumberOfSeats());
        booking.setSeatNumbers(request.getSeatNumbers());
        booking.setTotalAmount(request.getNumberOfSeats() * 10.0); // Sample price
        booking.setStatus(Booking.Status.PENDING);
        booking.setBookingReference(generateBookingReference());
        booking.setExpiryTime(LocalDateTime.now().plusMinutes(15)); // 15 min to complete payment
        
        return bookingRepository.save(booking);
    }
    
    public Booking confirmBooking(Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus(Booking.Status.CONFIRMED);
        return bookingRepository.save(booking);
    }
    
    public Booking cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus(Booking.Status.CANCELLED);
        return bookingRepository.save(booking);
    }
    
    private String generateBookingReference() {
        return "BKG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
