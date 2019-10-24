package com.example.aviasales2.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "cityRef1")
    @JoinColumn(name = "city_from")
    City city_from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "cityRef2")
    @JoinColumn(name = "city_dest")
    City city_dest;

    int price;
    int full_count_seats;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "transRef")
    @JoinColumn(name = "transport_id")
    Transport transport;

    Timestamp date_from;

    Timestamp date_dest;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFull_count_seats() {
        return full_count_seats;
    }

    public void setFull_count_seats(int full_count_seats) {
        this.full_count_seats = full_count_seats;
    }

    public Timestamp getDate_from() {
        return date_from;
    }

    public void setDate_from(Timestamp date_from) {
        this.date_from = date_from;
    }

    public Timestamp getDate_dest() {
        return date_dest;
    }

    public void setDate_dest(Timestamp date_dest) {
        this.date_dest = date_dest;
    }

    public City getCity_from() {
        return city_from;
    }

    public void setCity_from(City city_from) {
        this.city_from = city_from;
    }

    public City getCity_dest() {
        return city_dest;
    }

    public void setCity_dest(City city_dest) {
        this.city_dest = city_dest;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}
