package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class TourDTO {
    Timestamp date;
    List <CommentsDTO> comments;
    private Long tourId;
    @NotBlank(message = "The tour name should be not null!")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z\\s]+$", message = "Bad tour name!")
    @Size(max = 40)
    private String name;
    @NotBlank(message = "The tour price should be not null!")
    @Pattern(regexp = "[0-9]+$", message = "Filed error: price. Only positive digits")
    @Size(max = 6)
    private String price;
    @Size(max = 3)
    @Pattern(regexp = "[0-9]+$", message = "Bad duration number!")
    @NotBlank(message = "Duration of the tour should be not null!")
    private String duration;
    @NotBlank(message = "The city should be not null!")
    @Size(max = 20)
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z\\s]+$", message = "Bad city name!")
    private String city_destination;
    @Pattern(regexp = "[0-5]", message = "Bad rating number!(0-5)")
    private String rating;
    private long hotel;
    private long cityId;
    private long company;
    private List <UserDTO> users;
    private BigDecimal commentRating;

    public TourDTO() {
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCity_destination() {
        return city_destination;
    }

    public void setCity_destination(String city_destination) {
        this.city_destination = city_destination;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public long getHotel() {
        return hotel;
    }

    public void setHotel(long hotel) {
        this.hotel = hotel;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getCompany() {
        return company;
    }

    public void setCompany(long company) {
        this.company = company;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List <UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List <UserDTO> users) {
        this.users = users;
    }

    public List <CommentsDTO> getComments() {
        return comments;
    }

    public void setComments(List <CommentsDTO> comments) {
        this.comments = comments;
    }
}
