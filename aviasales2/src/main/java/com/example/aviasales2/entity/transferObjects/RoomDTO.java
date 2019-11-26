package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RoomDTO {
    private Long roomId;
    private int roomCapacity;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private Double dailyCost;
    private String status;
    private long hotel;
    private double description;
    private String image;
    private String type;

    public RoomDTO() {
    }
}
