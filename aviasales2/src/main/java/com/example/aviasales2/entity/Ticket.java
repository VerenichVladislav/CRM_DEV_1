package com.example.aviasales2.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String lastName;
    private Timestamp date;
    private long tripId;

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    private long cityDest;
    private long cityFrom;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public Ticket(String name, String lastName, long tripId, Timestamp date, long cityFrom, long cityDest, BigDecimal price, User buyer) {
        this.name = name;
        this.lastName = lastName;
        this.date = date;
        this.cityFrom = cityFrom;
        this.cityDest = cityDest;
        this.tripId = tripId;
        this.price = price;
        this.buyer = buyer;
    }

    @ManyToOne
    @JoinTable (name="active_booking",
            joinColumns=@JoinColumn (name="ticket_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private User buyer;

    public User getBuyer(){
        return buyer;
    }
    public Long getBuyerId() {
        return buyer.getId();
    }

    public Ticket(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Ticket (){}

    public void setBuyer(User user) {
        this.buyer=buyer;
    }

    public void setCityFrom(long cityFrom) {
        this.cityFrom = cityFrom;
    }

    public void setCityDest(long cityDest) {
        this.cityDest = cityDest;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public void setName(String name) {
        this.name=name;
    }
}
