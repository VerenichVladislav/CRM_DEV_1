package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Hotel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long hotelId;

    private String country;
    private String address;
    private Short rating;
    private String hotelName;
    private String phoneNumber;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Tour> tours;

    public Hotel(){}

    public long getHotelId() {
        return hotelId;
    }
}
