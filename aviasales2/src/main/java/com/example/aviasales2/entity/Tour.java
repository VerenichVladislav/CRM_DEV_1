package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String name;
    private int price;
    Timestamp date;

    private int duration;
    private String city_destination;
    private short rating;


    @ManyToMany
    @JoinTable (name="history",
            joinColumns=@JoinColumn (name="tour_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> users;

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_id")
    private Hotel hotel;


    enum status{
        ONLINE,
        OFFLINE
    };

    public Tour(){
    }

    @JsonIgnore
    public List<User> getUsers() {
        return users;
    }

    @JsonIgnore
    public City getCity() {
        return city;
    }

}