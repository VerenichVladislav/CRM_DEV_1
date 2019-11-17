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

    public String getCityDest() {
        return cityDest;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public void setCityDest(String cityDest) {
        this.cityDest = cityDest;
    }
}
