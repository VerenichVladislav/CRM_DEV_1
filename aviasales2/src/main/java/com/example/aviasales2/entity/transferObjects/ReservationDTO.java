package com.example.aviasales2.entity.transferObjects;

import com.example.aviasales2.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

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
