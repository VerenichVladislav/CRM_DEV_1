package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class CityDTO {
    private Long cityId;
    @NotBlank(message = "City name must be not null")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z\\\\s]+$", message = "Bad city name")
    @Size(max = 20)
    private String cityName;
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z\\\\s]+$", message = "Bad country name")
    @NotBlank(message = "The country must be not null")
    @Size(max = 20)
    private String country;
    @Pattern(regexp = "[0-9]+$", message = "Bad population number")
    @Size(max = 11)
    private String population;
    private Set <TripDTO> trip_from;
    private Set <TripDTO> trip_dest;
    private Set <TourDTO> tours;
    private Set <HotelDTO> hotels;
    private String image;
    private String lat;
    private String lng;

    public CityDTO() {
    }
}
