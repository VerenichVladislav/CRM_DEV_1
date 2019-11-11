package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference(value = "cityRef2")
    @JoinColumn(name = "city_dest")
    private City cityDest;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference(value = "cityRef1")
    @JoinColumn(name = "city_from")
    private City cityFrom;

    private BigDecimal price;
    @ManyToOne
    @JoinTable (name="active_booking",
            joinColumns=@JoinColumn (name="ticket_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private User buyer;

    public Ticket(String name, String lastName, long tripId, Timestamp date, City cityFrom, City cityDest, BigDecimal price, User buyer) {
        this.name = name;
        this.lastName = lastName;
        this.date = date;
        this.cityFrom = cityFrom;
        this.cityDest = cityDest;
        this.tripId = tripId;
        this.price = price;
        this.buyer = buyer;
    }

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

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setBuyer(User user) {
        this.buyer=buyer;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public void setCityDest(City cityDest) {
        this.cityDest = cityDest;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public void setName(String name) {
        this.name=name;
    }
}
