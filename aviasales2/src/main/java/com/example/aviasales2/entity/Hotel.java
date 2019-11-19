package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelId;

    private String country;
    private String address;
    private Short rating;
    private String hotelName;
    private String phoneNumber;
    private String description;
    private String image;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms;

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @OneToMany(mappedBy = "hotel",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Tour> tours;

    public List<Reservation> getReservations() {
        return reservations;
    }

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comments> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city")
    private City city;

    private enum HotelConveniences {

    }

    public Hotel() {
    }

}
