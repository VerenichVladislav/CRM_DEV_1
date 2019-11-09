package com.example.aviasales2.entity.transferObjects;

import java.util.List;

public class TransportDTO {
    public long id;
    String name;
    int baggage;
    int company;
    //List<Long> trips;

    public TransportDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaggage() {
        return baggage;
    }

    public void setBaggage(int baggage) {
        this.baggage = baggage;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

//    public List<Long> getTrips() {
//        return trips;
//    }
//
//    public void setTrips(List<Long> trips) {
//        this.trips = trips;
//    }
}
