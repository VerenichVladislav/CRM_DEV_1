package com.example.aviasales2.entity.transferObjects;

import java.sql.Timestamp;

public class RoomDTO {
    private Long roomId;
    private int roomCapacity;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private Double dailyCost;
    private String status;
    private HotelDTO hotel;

    public RoomDTO() {
    }
}
