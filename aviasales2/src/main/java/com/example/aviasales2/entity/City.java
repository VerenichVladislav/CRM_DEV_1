package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@Entity
@Builder
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

//    @OneToOne(optional = false, mappedBy = "city")
//    private Tour tour;

//    @JsonIgnore
//    public Tour getTour() {
//        return tour;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Integer foundationDate) {
        this.foundationDate = foundationDate;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Set<Trip> getTrip_from() {
        return trip_from;
    }

    public void setTrip_from(Set<Trip> trip_from) {
        this.trip_from = trip_from;
    }

    public Set<Trip> getTrip_dest() {
        return trip_dest;
    }

    public void setTrip_dest(Set<Trip> trip_dest) {
        this.trip_dest = trip_dest;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    public City() {
    }


}