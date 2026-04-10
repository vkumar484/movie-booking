package com.sapient.bookingsystem.cache;

import com.sapient.bookingsystem.entity.Show;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ShowCache {

    // movie + city → shows
    private final Map<String, List<Show>> movieCityShows = new ConcurrentHashMap<>();

    public List<Show> getShows(String movie, String city) {
        return movieCityShows.getOrDefault(movie + "_" + city, List.of());
    }

    public void addShow(String movie, String city, Show show) {
        movieCityShows.computeIfAbsent(movie + "_" + city, k -> new ArrayList<>())
                .add(show);
    }
}
