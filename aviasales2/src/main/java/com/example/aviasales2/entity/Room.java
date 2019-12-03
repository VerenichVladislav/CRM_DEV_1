package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private Double dailyCost;
    private String status;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ElementCollection(targetClass = RoomConvenience.class)
    @CollectionTable(name = "room_conveniences",
            joinColumns = @JoinColumn(name = "room_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "convenience_id")
    private List<RoomConvenience> roomConvenience;
}
