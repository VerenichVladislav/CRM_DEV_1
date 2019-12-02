package com.example.aviasales2.entity.transferObjects;

import com.example.aviasales2.entity.RoomConvenience;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class RoomDTO {
    private Long roomId;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private Double dailyCost;
    private String status;
    private long hotel;
    private double description;
    private String image;
    private String type;
    private List<RoomConvenience> roomConvenience;

    public RoomDTO() {
    }
}
