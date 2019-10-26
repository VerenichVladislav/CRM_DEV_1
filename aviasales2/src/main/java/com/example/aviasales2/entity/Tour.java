package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "tour")
@NoArgsConstructor
public class Tour {
    @Id
    @GeneratedValue

    private long id;
    private String name;
    private int price;
    Timestamp date;

    private int duration;
    private String city_destination;
    private short rating;
    private long hotelId;



    @ManyToMany
    @JoinTable (name="history",
            joinColumns=@JoinColumn (name="tour_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> users;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_id")
    private Hotel hotel;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference(value = "cityRef3")
    private City cityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @JsonBackReference
    Company company;

    enum status{
        ONLINE,
        OFFLINE
    }

    @JsonIgnore
    public List<User> getUsers() {
        return users;
    }

    public Tour(City cityId) {
        this.cityId = cityId;
    }
}