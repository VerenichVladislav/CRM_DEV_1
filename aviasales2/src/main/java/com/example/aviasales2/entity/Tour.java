package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Tour {
    @Id
    @GeneratedValue

    private long id;
    private String name;
    private int price;
    private String date;
    private int duration;
    private short rating;
    private int hotel_id;
    private int company_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference(value = "cityRef3")
    private City cityId;
    enum status{
        ONLINE,
        OFFLINE
    }
}