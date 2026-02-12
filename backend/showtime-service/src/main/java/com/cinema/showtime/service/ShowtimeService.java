package com.cinema.showtime.service;

import com.cinema.showtime.entity.Showtime;
import com.cinema.showtime.repository.ShowtimeRepository;
import com.cinema.common.web.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShowtimeService {
    
    private final ShowtimeRepository showtimeRepository;
    
    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }
    
    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }
    
    public List<Showtime> getShowtimesByMovieId(Long movieId) {
        return showtimeRepository.findByMovieId(movieId);
    }
    
    public Showtime getShowtimeById(Long id) {
        return showtimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Showtime not found with id: " + id));
    }
    
    public Showtime createShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }
    
    public Showtime updateShowtime(Long id, Showtime showtimeDetails) {
        Showtime showtime = getShowtimeById(id);
        
        showtime.setMovieId(showtimeDetails.getMovieId());
        showtime.setScreenName(showtimeDetails.getScreenName());
        showtime.setStartTime(showtimeDetails.getStartTime());
        showtime.setEndTime(showtimeDetails.getEndTime());
        showtime.setPrice(showtimeDetails.getPrice());
        showtime.setTotalSeats(showtimeDetails.getTotalSeats());
        showtime.setAvailableSeats(showtimeDetails.getAvailableSeats());
        showtime.setStatus(showtimeDetails.getStatus());
        
        return showtimeRepository.save(showtime);
    }
    
    public void deleteShowtime(Long id) {
        Showtime showtime = getShowtimeById(id);
        showtimeRepository.delete(showtime);
    }
    
    public boolean checkAvailability(Long showtimeId, Integer requestedSeats) {
        Showtime showtime = getShowtimeById(showtimeId);
        return showtime.getAvailableSeats() >= requestedSeats;
    }
}
