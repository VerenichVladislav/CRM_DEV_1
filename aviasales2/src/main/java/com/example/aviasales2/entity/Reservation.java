package com.example.aviasales2.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;
    private Long roomId;
    private Long userId;
    private Timestamp сheckIn;
    private Timestamp сheckOut;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public void setСheckIn(Timestamp сheckIn) {
        this.сheckIn = сheckIn;
    }

    public void setСheckOut(Timestamp сheckOut) {
        this.сheckOut = сheckOut;
    }
}
