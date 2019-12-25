package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "tour")
@NoArgsConstructor
public class Tour {

    BigDecimal price;
    Timestamp date;
    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List <Comment> comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    Company company;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tourId;
    private String name;
    private int duration;
    private String city_destination;
    private short rating;
    private BigDecimal commentRating = new BigDecimal(0);
    @ManyToMany
    @JoinTable(name = "history",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List <User> users;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City cityId;

    public BigDecimal getCommentRating() {
        return commentRating;
    }

    public void setCommentRating(BigDecimal commentRating) {
        this.commentRating = commentRating;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }


    //    enum status{
//        ONLINE,
//        OFFLINE
//    }

}
