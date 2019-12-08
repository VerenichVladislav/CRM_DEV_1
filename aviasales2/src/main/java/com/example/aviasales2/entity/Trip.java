package com.example.aviasales2.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long tripId;
    BigDecimal price;
    private int fullCountSeats;
    private Timestamp dateFrom;
    private Timestamp dateDest;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_from")
    private City cityFrom;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_dest")
    private City cityDest;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id")
    private Transport transport;

    public Trip() {
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityDest() {
        return cityDest;
    }

    public void setCityDest(City cityDest) {
        this.cityDest = cityDest;
    }
}
