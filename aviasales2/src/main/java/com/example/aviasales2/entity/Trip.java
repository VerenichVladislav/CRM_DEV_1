package com.example.aviasales2.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
    long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference(value = "cityRef1")
    @JoinColumn(name = "city_from")
    private City cityFrom;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference(value = "cityRef2")
    @JoinColumn(name = "city_dest")
    private City cityDest;

    BigDecimal price;
    private int fullCountSeats;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id")
    private Transport transport;

    private Timestamp dateFrom;

    private Timestamp dateDest;

    public Trip (){}

    public City getCityDest() {
        return cityDest;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public long getId() {
        return id;
    }

    public Timestamp getDateDest() {
        return dateDest;
    }


    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setFullCountSeats(int fullCountSeats) {
        this.fullCountSeats = fullCountSeats;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getFullCountSeats() {
        return fullCountSeats;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCityDest(City cityDest) {
        this.cityDest = cityDest;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public void setDateDest(Timestamp dateDest) {
        this.dateDest = dateDest;
    }

    public void setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Transport getTransport() {
        return transport;
    }

}
