package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @ElementCollection(targetClass = RoomType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "room_type", joinColumns = @JoinColumn(name = "hotel_id"))
    @Enumerated(EnumType.ORDINAL)
    private Set<RoomType> roomTypes;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Room> rooms;

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @OneToMany(mappedBy = "hotel",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Reservation> reservations;


    public List<Reservation> getReservations() {
        return reservations;
    }

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comments> comments;


    private String city;

    public long getHotelId() {
        return this.hotelId;
    }

    private enum HotelConveniences {

    }

    public Hotel() {
    }

}
