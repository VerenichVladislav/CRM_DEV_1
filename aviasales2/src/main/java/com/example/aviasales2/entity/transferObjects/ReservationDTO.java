package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ReservationDTO {
    private Long reservationId;
    private Long roomId;
    private UserDTO buyer;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private long hotel;
}
