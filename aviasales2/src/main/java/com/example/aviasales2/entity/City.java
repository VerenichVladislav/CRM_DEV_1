package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull(message = "City name must be not null")
    @Size(max = 15)
    private String cityName;
    @NotNull(message = "The country must be not null")
    @Size(max = 15)
    private String country;
    private String foundationDate;
    private String population;

    @OneToMany(mappedBy = "cityFrom", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<Trip> trip_from;

    @OneToMany(mappedBy = "cityDest", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<Trip> trip_dest;

    @OneToMany(mappedBy = "tourId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Tour> tours;


    public City() {
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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

    public String getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(String foundationDate) {
        this.foundationDate = foundationDate;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
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
}
