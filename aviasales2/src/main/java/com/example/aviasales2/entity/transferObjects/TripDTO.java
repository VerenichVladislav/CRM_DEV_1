package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class TripDTO {
    private Long tripId;
    private String cityFrom;
    private String cityDest;
    private BigDecimal price;
    private int fullCountSeats;
    private String transport;
    private Timestamp dateFrom;
    private Timestamp dateDest;

    public TripDTO() {
    }

}
