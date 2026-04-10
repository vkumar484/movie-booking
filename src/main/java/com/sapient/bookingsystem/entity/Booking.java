package com.sapient.bookingsystem.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Booking {
    private String id;
    private String showId;
    private List<String> seatIds;
    private String userId;
}
