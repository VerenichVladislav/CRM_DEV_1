package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Hotel{
    @Id
    @GeneratedValue
    long hotelId;
    String country;
    String address;
    short rating;
    String hotelName;
    String phoneNumber;

    public Hotel(){}
}
