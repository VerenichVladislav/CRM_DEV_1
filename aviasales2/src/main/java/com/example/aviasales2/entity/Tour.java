package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;



@Data
@AllArgsConstructor
@Entity
@Table(name = "tour")
@NoArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
    @JsonBackReference(value = "cityRef3")
    private City cityId;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "tourcomm")
    List<Comments> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @JsonBackReference
    Company company;


//    enum status{
//        ONLINE,
//        OFFLINE
//    }

    @JsonIgnore
    public List<User> getUsers() {
        return users;
    }
}