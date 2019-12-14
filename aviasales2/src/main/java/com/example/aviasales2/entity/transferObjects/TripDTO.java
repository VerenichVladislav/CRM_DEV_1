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
    private long cityFrom;
    private long cityDest;
    @NotBlank(message = "Price should be not null!")
    @Pattern(regexp = "[0-9]+$", message = "bad number of price")
    @Size(max = 10)
    private String price;
    @NotBlank(message = "Count of seats should be >0!")
    @Size(max = 3)
    @Pattern(regexp = "[0-9]+$", message = "Bad number of seats")
    private String fullCountSeats;
    private long transport;
    private Timestamp dateFrom;
    private Timestamp dateDest;
    private int count = 1;

    public TripDTO() {
    }
}
