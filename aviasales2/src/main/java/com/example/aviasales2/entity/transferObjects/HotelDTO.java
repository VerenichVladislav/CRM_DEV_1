package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelDTO {
    private Long hotelId;

    private String country;
    private String address;
    private Short rating;
    private String hotelName;
    private String phoneNumber;
    private List<RoomDTO> rooms;
    private List<TourDTO> tours;
    private List<CommentsDTO> comments;
    private CityDTO city;


    public HotelDTO() {
    }

}
