package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    private String cityName;
    private String country;
    private Integer foundationDate;
    private Long population;

    @OneToMany(mappedBy = "cityFrom", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<Trip> trip_from;

    @OneToMany(mappedBy = "cityDest", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<Trip> trip_dest;

    @OneToMany(mappedBy = "tourId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Tour> tours;

    @OneToMany(mappedBy = "city",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Hotel> hotels;


    public City() {
    }
}