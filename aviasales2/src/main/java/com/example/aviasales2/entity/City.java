package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    private String cityName;
    private String country;
    private Integer foundationDate;
    private Long population;

    public City() {
    }


}