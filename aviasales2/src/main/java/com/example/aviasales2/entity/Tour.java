package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Tour {
    @Id
    @GeneratedValue

    long id;
    String name;
    int price;
    String date;
    long user_id;
    int duration;
    String city_destination;
    short rating;
    int hotel_id;
    int company_id;
    enum status{
        ONLINE,
        OFFLINE
    };

    public Tour(){
    }
}