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
    @JsonBackReference(value = "tourcomm")
    @JoinColumn(name = "tour_id")
    private Tour tour;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "compcomm")
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "hotelcomm")
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Comments(){}

}
