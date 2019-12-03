package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cityId;

    @NotBlank(message = "City name must be not null")
    @Size(max = 15)
    private String cityName;
    @NotBlank(message = "The country must be not null")
    @Size(max = 15)
    private String country;
    private Long population;

    @OneToMany(mappedBy = "cityFrom", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set <Trip> trip_from;

    @OneToMany(mappedBy = "cityDest", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set <Trip> trip_dest;

    @OneToMany(mappedBy = "cityId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set <Tour> tours;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <Hotel> hotels;
    private String image;
    private String lat;
    private String lng;

    public City() {
    }

    public List <Hotel> getHotels() {
        return hotels;
    }
}
