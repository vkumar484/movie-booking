package com.sapient.bookingsystem.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Show {
    private String id;
    private String movieName;
    private String theatreId;
    private LocalDateTime startTime;
}
