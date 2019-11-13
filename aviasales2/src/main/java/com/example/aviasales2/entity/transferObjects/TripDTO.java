package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class TripDTO {
    private long id;
    private String cityFrom;
    private String cityDest;
    private BigDecimal price;
    private int fullCountSeats;
    private long transport;
    private Timestamp dateFrom;
    private Timestamp dateDest;

    public TripDTO() {
    }

}
