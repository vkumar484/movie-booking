package com.sapient.bookingsystem.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowSeat {
    private String seatId;
    private String showId;
    private SeatStatus status;
    private long lockedAt;
}
