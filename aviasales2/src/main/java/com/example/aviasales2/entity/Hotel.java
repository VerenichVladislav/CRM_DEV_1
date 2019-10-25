package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Hotel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long hotelId;

    private String country;
    private String address;
    private Short rating;
    private String hotelName;
    private String phoneNumber;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Tour> tours;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "hotelcomm")
    List<Comments> comments;

    public Hotel(){}

    public long getHotelId() {
        return hotelId;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
