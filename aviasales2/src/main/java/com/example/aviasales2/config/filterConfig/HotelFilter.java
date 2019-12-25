package com.example.aviasales2.config.filterConfig;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HotelFilter {
    private String city;
    private Short rating;
    private String dateFrom;
    private String dateTo;
    private List<String> hotelConveniences = new ArrayList<>();
}
