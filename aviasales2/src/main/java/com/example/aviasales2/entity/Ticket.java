package com.example.aviasales2.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    private Long ticketId;
    @NotBlank(message = "Name must not null")
    @Size(max = 20)
    private String name;
    @Size(max = 20)
    @NotBlank(message = "Last name must not null")
    private String lastName;
    private Timestamp date;
    private long tripId;
    //@Pattern(regexp = "[\\-?\\d+(\\.\\d{0,})?]", message ="Bad digit view" )
    private BigDecimal price;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_dest")
    private City cityDest;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_from")
    private City cityFrom;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
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

    public Ticket(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Ticket() {
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User user) {
        this.buyer = user;
    }

    public Long getBuyerId() {
        return buyer.getUserId();
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public void setCityDest(City cityDest) {
        this.cityDest = cityDest;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }
}
