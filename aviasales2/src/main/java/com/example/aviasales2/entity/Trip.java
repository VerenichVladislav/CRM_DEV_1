package com.example.aviasales2.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    private int price;
    private int fullCountSeats;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id")
    private Transport transport;

    private Timestamp dateFrom;

    private Timestamp dateDest;

    public Trip (){}

}
