package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class CityDTO {
    private Long id;
    private String cityName;
    private String country;
    private Integer foundationDate;
    private Long population;
    private Set<TripDTO> trip_from;
    private Set<TripDTO> trip_dest;
    private Set<TourDTO> tours;

    public CityDTO() {
    }
}
