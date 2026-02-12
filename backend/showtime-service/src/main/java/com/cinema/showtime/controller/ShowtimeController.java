package com.cinema.showtime.controller;

import com.cinema.showtime.entity.Showtime;
import com.cinema.showtime.service.ShowtimeService;
import com.cinema.common.web.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
public class ShowtimeController {
    
    private final ShowtimeService showtimeService;
    
    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Showtime>>> getAllShowtimes(
            @RequestParam(required = false) Long movieId) {
        List<Showtime> showtimes = movieId != null 
            ? showtimeService.getShowtimesByMovieId(movieId)
            : showtimeService.getAllShowtimes();
        return ResponseEntity.ok(ApiResponse.success(showtimes));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Showtime>> getShowtimeById(@PathVariable Long id) {
        Showtime showtime = showtimeService.getShowtimeById(id);
        return ResponseEntity.ok(ApiResponse.success(showtime));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<Showtime>> createShowtime(@RequestBody Showtime showtime) {
        Showtime createdShowtime = showtimeService.createShowtime(showtime);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Showtime created successfully", createdShowtime));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Showtime>> updateShowtime(
            @PathVariable Long id, @RequestBody Showtime showtime) {
        Showtime updatedShowtime = showtimeService.updateShowtime(id, showtime);
        return ResponseEntity.ok(ApiResponse.success("Showtime updated successfully", updatedShowtime));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteShowtime(@PathVariable Long id) {
        showtimeService.deleteShowtime(id);
        return ResponseEntity.ok(ApiResponse.success("Showtime deleted successfully", null));
    }
    
    @GetMapping("/{id}/availability")
    public ResponseEntity<ApiResponse<Boolean>> checkAvailability(
            @PathVariable Long id, @RequestParam Integer seats) {
        boolean available = showtimeService.checkAvailability(id, seats);
        return ResponseEntity.ok(ApiResponse.success(available));
    }
}
