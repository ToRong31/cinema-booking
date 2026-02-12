package com.cinema.showtime.repository;

import com.cinema.showtime.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByMovieId(Long movieId);
    List<Showtime> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Showtime> findByStatus(Showtime.Status status);
}
