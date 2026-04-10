package com.sapient.bookingsystem.cache;

import com.sapient.bookingsystem.entity.ShowSeat;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SeatInventoryCache {

    // showId → seatId → seat
    private final Map<String, Map<String, ShowSeat>> cache = new ConcurrentHashMap<>();

    public Map<String, ShowSeat> getSeats(String showId) {
        return cache.getOrDefault(showId, new ConcurrentHashMap<>());
    }

    public ShowSeat getSeat(String showId, String seatId) {
        return cache.get(showId).get(seatId);
    }

    public void putSeat(String showId, ShowSeat seat) {
        cache.computeIfAbsent(showId, k -> new ConcurrentHashMap<>())
                .put(seat.getSeatId(), seat);
    }

    public void updateSeat(String showId, ShowSeat seat) {
        cache.get(showId).put(seat.getSeatId(), seat);
    }
}
