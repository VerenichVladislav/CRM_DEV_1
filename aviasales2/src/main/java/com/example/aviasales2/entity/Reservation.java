package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private Timestamp checkIn;
    private Timestamp checkOut;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User buyer;

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public void setCheckIn(Timestamp checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Timestamp checkOut) {
        this.checkOut = checkOut;
    }

    public Timestamp getCheckOut() {
        return checkOut;
    }

    public Timestamp getCheckIn() {
        return checkIn;
    }

    public Long getRoomId() {
        return roomId;
    }
}
