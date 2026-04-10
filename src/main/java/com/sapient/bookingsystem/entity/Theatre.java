package com.sapient.bookingsystem.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Theatre {
    private String id;
    private String name;
    private String city;
}
