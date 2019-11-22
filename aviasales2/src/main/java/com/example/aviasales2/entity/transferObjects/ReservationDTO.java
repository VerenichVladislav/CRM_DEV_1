package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ReservationDTO {
    private Long reservationId;
    private Long roomId;
    private Long userId;
    private Timestamp сheckIn;
    private Timestamp сheckOut;
    private long hotel;
}
