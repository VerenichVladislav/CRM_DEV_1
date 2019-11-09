package com.example.aviasales2.entity.transferObjects;

public class TripDTO {
    private long id;
    private long cityFrom;
    private long cityDest;
    private int price;
    private int fullCountSeats;
    private long transport;

    public TripDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(long cityFrom) {
        this.cityFrom = cityFrom;
    }

    public long getCityDest() {
        return cityDest;
    }

    public void setCityDest(long cityDest) {
        this.cityDest = cityDest;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFullCountSeats() {
        return fullCountSeats;
    }

    public void setFullCountSeats(int fullCountSeats) {
        this.fullCountSeats = fullCountSeats;
    }

    public long getTransport() {
        return transport;
    }

    public void setTransport(long transport) {
        this.transport = transport;
    }


}
