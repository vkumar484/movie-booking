package com.sapient.bookingsystem.service;

import com.sapient.bookingsystem.cache.SeatInventoryCache;
import com.sapient.bookingsystem.entity.Booking;
import com.sapient.bookingsystem.entity.SeatStatus;
import com.sapient.bookingsystem.entity.ShowSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final SeatInventoryCache cache;

    private final Map<String, ReentrantLock> locks = new ConcurrentHashMap<>();

    public Booking bookTickets(String userId, String showId, List<String> seatIds) {

        List<ReentrantLock> acquiredLocks = new ArrayList<>();

        try {
            // lock all seats
            for (String seatId : seatIds) {
                ReentrantLock lock = locks.computeIfAbsent(seatId, k -> new ReentrantLock());
                lock.lock();
                acquiredLocks.add(lock);
            }

            // Fetch seat details
            List<ShowSeat> seats = seatIds.stream()
                    .map(seatId -> cache.getSeat(showId, seatId))
                    .toList();

            // check availability
            boolean unavailable = seats.stream()
                    .anyMatch(seat -> seat.getStatus() != SeatStatus.AVAILABLE);

            if (unavailable) {
                throw new RuntimeException("Seats not available");
            }

            // lock seats temporarily
            seats.forEach(seat -> {
                seat.setStatus(SeatStatus.LOCKED);
                seat.setLockedAt(System.currentTimeMillis());
                cache.updateSeat(showId, seat);
            });

         // Create booking
            Booking booking = Booking.builder()
                    .id(UUID.randomUUID().toString())
                    .userId(userId)
                    .showId(showId)
                    .seatIds(seatIds)
                    .build();

            // Confirm booking and update seat status
            seats.forEach(seat -> {
                seat.setStatus(SeatStatus.BOOKED);
                cache.updateSeat(showId, seat);
            });

            return booking;

        } finally {
            acquiredLocks.forEach(ReentrantLock::unlock);
        }
    }
}
