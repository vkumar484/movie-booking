package com.sapient.bookingsystem.controller;

import com.sapient.bookingsystem.dto.ShowResponse;
import com.sapient.bookingsystem.entity.Booking;
import com.sapient.bookingsystem.service.BookingService;
import com.sapient.bookingsystem.service.BrowseService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {

    private final BrowseService browseService;
    private final BookingService bookingService;


    @GetMapping("/shows")
    public List<ShowResponse> browseShows(
            @RequestParam String movie,
            @RequestParam String city,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return browseService.browseShows(movie, city, date);
    }


    @PostMapping("/shows/{showId}/book")
    public Booking bookTickets(
            @PathVariable String showId,
            @RequestParam String userId,
            @RequestBody List<String> seatIds) {

        return bookingService.bookTickets(userId, showId, seatIds);
    }
}
