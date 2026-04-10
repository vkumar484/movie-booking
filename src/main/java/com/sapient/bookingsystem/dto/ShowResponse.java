package com.sapient.bookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ShowResponse {
    private String theatreName;
    private LocalDateTime showTime;
}
