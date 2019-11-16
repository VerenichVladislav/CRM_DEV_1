package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private Long id;

    private String cityName;
    private String country;
    private Integer foundationDate;
    private Long population;

    @OneToMany(mappedBy = "cityFrom", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonManagedReference(value = "cityRef1")
    private Set<Trip> trip_from;

    @OneToMany(mappedBy = "cityDest", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonManagedReference(value = "cityRef2")
    private Set<Trip> trip_dest;

    @OneToMany(mappedBy = "cityId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "cityRef3")
    private Set<Tour> tours;

//    @OneToMany(mappedBy = "hotelId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "hotelRef")
//    private Set<Hotel> hotels;


    public String getCityName() {
        return cityName;
    }


    public Long getId() {
        return id;
    }
    //    @OneToOne(optional = false, mappedBy = "city")
//    private Tour tour;

//    @JsonIgnore
//    public Tour getTour() {
//        return tour;
//    }


    public City() {
    }


}