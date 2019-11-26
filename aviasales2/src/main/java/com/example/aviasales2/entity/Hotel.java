package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "The country must be not null")
    @Size(max = 20)
    private String country;
    @NotBlank(message = "The address must be not null")
    @Size(max = 40)
    private String address;
    private short rating;
    @Size(max = 20)
    @NotBlank(message = "The hotel name must be not null")
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="city_id")
    private City city;

    @ElementCollection(targetClass = HotelConvenience.class)
    @CollectionTable(name = "hotel_conveniences",
            joinColumns = @JoinColumn(name = "hotel_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "convenience_id")
    private List<HotelConvenience> hotelConveniences;

    public Hotel() {
    }

}
