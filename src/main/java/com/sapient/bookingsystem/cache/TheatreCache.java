package com.sapient.bookingsystem.cache;

import com.sapient.bookingsystem.entity.Theatre;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TheatreCache {

    private final Map<String, Theatre> theatreMap = new ConcurrentHashMap<>();

    public Theatre getTheatre(String theatreId) {
        return theatreMap.get(theatreId);
    }

    public void addTheatre(Theatre theatre) {
        theatreMap.put(theatre.getId(), theatre);
    }
}
