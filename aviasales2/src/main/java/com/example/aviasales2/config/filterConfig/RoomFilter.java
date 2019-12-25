package com.example.aviasales2.config.filterConfig;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoomFilter {
    private Double minPrice = 0.0;
    private Double maxPrice = 2000.0;
    private List <String> roomConveniences = new ArrayList<>();
}
