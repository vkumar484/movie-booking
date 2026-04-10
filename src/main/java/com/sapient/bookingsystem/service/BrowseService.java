package com.sapient.bookingsystem.service;

import com.sapient.bookingsystem.cache.ShowCache;
import com.sapient.bookingsystem.cache.TheatreCache;
import com.sapient.bookingsystem.dto.ShowResponse;
import com.sapient.bookingsystem.entity.Show;
import com.sapient.bookingsystem.entity.Theatre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrowseService {

    private final ShowCache showCache;
    private final TheatreCache theatreCache;

    public List<ShowResponse> browseShows(String movie, String city, LocalDate date) {

        List<Show> shows = showCache.getShows(movie, city);

        return shows.stream()
                .filter(show -> show.getStartTime().toLocalDate().equals(date))
                .map(show -> {
                    Theatre theatre = theatreCache.getTheatre(show.getTheatreId());

                    return new ShowResponse(
                            theatre.getName(),
                            show.getStartTime()
                    );
                })
                .toList();
    }
}
