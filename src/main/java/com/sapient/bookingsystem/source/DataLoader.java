package com.sapient.bookingsystem.source;

import com.sapient.bookingsystem.cache.SeatInventoryCache;
import com.sapient.bookingsystem.cache.ShowCache;
import com.sapient.bookingsystem.cache.TheatreCache;
import com.sapient.bookingsystem.entity.SeatStatus;
import com.sapient.bookingsystem.entity.Show;
import com.sapient.bookingsystem.entity.ShowSeat;
import com.sapient.bookingsystem.entity.Theatre;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final TheatreCache theatreCache;
    private final ShowCache showCache;
    private final SeatInventoryCache seatCache;

    @Override
    public void run(String... args) {

        // 🎭 Theatre
        Theatre t1 = Theatre.builder()
                .id("T1")
                .name("PVR Forum Mall")
                .city("Bangalore")
                .build();

        theatreCache.addTheatre(t1);

        // 🎬 Show
        Show s1 = Show.builder()
                .id("S1")
                .movieName("Avengers")
                .theatreId("T1")
                .startTime(LocalDateTime.now().plusHours(2))
                .build();

        showCache.addShow("Avengers", "Bangalore", s1);

        // 💺 Seats
        for (int i = 1; i <= 10; i++) {
            ShowSeat seat = ShowSeat.builder()
                    .seatId("A" + i)
                    .showId("S1")
                    .status(SeatStatus.AVAILABLE)
                    .build();

            seatCache.putSeat("S1", seat);
        }

        System.out.println("✅ Sample data loaded");
    }
}
