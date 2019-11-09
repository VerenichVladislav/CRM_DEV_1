package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourDTO {
    private Long id;
    private String name;
    private int price;
    private int duration;
    private String city_destination;
    private short rating;
    private long hotel;
    private long cityId;
    private long company;

    public TourDTO() {
    }
}
