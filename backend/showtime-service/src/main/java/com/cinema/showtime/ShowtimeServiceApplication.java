package com.cinema.showtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cinema.showtime", "com.cinema.common"})
public class ShowtimeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowtimeServiceApplication.class, args);
    }
}
