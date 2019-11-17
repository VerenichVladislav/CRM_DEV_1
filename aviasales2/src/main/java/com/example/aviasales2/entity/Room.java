package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    private int roomCapacity;
    private BigDecimal dailyCost;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "hotelRoom")
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    private enum RoomConveniences {

    }

    private enum RoomType {

    }


}
