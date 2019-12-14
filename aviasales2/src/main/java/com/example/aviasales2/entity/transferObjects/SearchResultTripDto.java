package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SearchResultTripDto {

    private long tripId;
    private String cityFrom;
    private String cityDest;
    private String price;
    private String fullCountSeats;
    private String transport;
    private Timestamp dateFrom;
    private Timestamp dateDest;
    private int count = 1;
}
