package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class TourDTO {
    private Long tourId;
    private String name;
    private BigDecimal price;
    private int duration;
    private String city_destination;
    private short rating;
    private long hotel;
    private long cityId;
    private long company;
    Timestamp date;
    private List<UserDTO> users;
    List<CommentsDTO> comments;


    public TourDTO() {
    }
}
