package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class HotelDTO {
    private Long hotelId;
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z\\s]+$", message = "{invalid.country}")
    @NotBlank(message = "Bad country name")
    @Size(max = 20)
    private String country;
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z\\s]+$", message = "{invalid.city}")
    @NotBlank(message = "Bad city name")
    @Size(max = 20)
    private String city;
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z0-9\\s]+$", message = "{invalid.address}")
    @NotBlank(message = "Bad address name")
    @Size(max = 40)
    private String address;
    @Pattern(regexp = "[0-5]", message = "invalid.rating")
    private String rating;
    @NotBlank(message = "Hotel name must be not null")
    @Size(max = 20)
    private String hotelName;
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", message = "{invalid.phoneNumber}")
    private String phoneNumber;
    private String description;
    private String image;
    private List<RoomDTO> rooms;
    private List<TourDTO> tours;
    private List<CommentsDTO> comments;
    private List<ReservationDTO> reservations;


    public HotelDTO() {
    }

}
