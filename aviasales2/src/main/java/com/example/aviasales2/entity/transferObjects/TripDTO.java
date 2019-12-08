package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
public class TripDTO {
    private Long tripId;
    private String cityFrom;
    private String cityDest;
    @NotBlank(message = "Price should be not null!")
    @Pattern(regexp = "[0-9]+$", message = "bad number of price")
    @Size(max = 10)
    private String price;
    @NotBlank(message = "Count of seats should be >0!")
    @Size(max = 3)
    @Pattern(regexp = "[0-9]+$", message = "Bad number of seats")
    private String fullCountSeats;
    private String transport;
    private Timestamp dateFrom;
    private Timestamp dateDest;

    public TripDTO() {
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityDest() {
        return cityDest;
    }

    public void setCityDest(String cityDest) {
        this.cityDest = cityDest;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }
}
