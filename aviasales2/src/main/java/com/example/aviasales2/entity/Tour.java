package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;



@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "tour")
@NoArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tourId;
    private  String name;
    BigDecimal price;
    Timestamp date;

    private int duration;
    private String city_destination;
    private short rating;


    @ManyToMany
    @JoinTable (name="history",
            joinColumns=@JoinColumn (name="tour_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> users;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City cityId;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Comments> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    Company company;

    public Hotel getHotel() {
        return hotel;
    }



    //    enum status{
//        ONLINE,
//        OFFLINE
//    }

}
