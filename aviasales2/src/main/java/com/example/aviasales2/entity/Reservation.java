package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;
    private Long roomId;
    private Long userId;
    private Timestamp CheckIn;
    private Timestamp CheckOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "hotelRoom")
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setCheckOut(Timestamp checkOut) {
        CheckOut = checkOut;
    }

    public void setCheckIn(Timestamp checkIn) {
        CheckIn = checkIn;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getCheckIn() {
        return CheckIn;
    }

    public Date getCheckOut() {
        return CheckOut;
    }
}
