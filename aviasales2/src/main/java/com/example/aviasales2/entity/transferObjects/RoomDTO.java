package com.example.aviasales2.entity.transferObjects;

import com.example.aviasales2.entity.RoomConvenience;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomDTO {
    private Long roomId;
    private Double dailyCost;
    private String status;
    private Long hotel;
    private String description;
    private String image;
    private String type;
    private List <RoomConvenience> roomConvenience;

    public RoomDTO() {
    }
}
