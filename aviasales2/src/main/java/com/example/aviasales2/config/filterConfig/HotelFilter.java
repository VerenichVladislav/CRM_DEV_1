package com.example.aviasales2.config.filterConfig;

import com.example.aviasales2.entity.City;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HotelFilter {
    private City city;
    private Short rating;
}
