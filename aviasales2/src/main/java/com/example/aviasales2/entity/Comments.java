package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String text;

    String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "tourcomm")
    @JoinColumn(name = "tour_id")
    Optional<Tour> tour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "compcomm")
    @JoinColumn(name = "company_id")
    Company company;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "hotelcomm")
    @JoinColumn(name = "hotel_id")
    Hotel hotel;

    public Comments(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Optional<Tour> getTour() {
        return tour;
    }

    public void setTour(Optional<Tour> tour) {
        this.tour = tour;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
