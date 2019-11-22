package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.prefs.AbstractPreferences;


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
    private String type = RoomType.ONE_SINGLE_BED.name();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private enum RoomConveniences {

    }


}
