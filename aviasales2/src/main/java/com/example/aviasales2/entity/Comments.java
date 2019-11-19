package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public String getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Comments(){}

}
