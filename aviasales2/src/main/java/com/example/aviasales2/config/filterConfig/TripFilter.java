package com.example.aviasales2.config.filterConfig;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripFilter {
    private String cityFrom;
    private String cityDest;
    private String dateFrom;

    public TripFilter (){}

}
