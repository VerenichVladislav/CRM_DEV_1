package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    private int roomCapacity;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private BigDecimal dailyCost;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private enum RoomConveniences {

    }

    private enum RoomType {

    }


}
