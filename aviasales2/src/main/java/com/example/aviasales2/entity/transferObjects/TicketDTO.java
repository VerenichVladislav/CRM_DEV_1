package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
public class TicketDTO {
    private Long ticketId;
    private String name;
    private String lastName;
    private Timestamp date;
    private long tripId;
    private long cityDest;
    private long cityFrom;
    private BigDecimal price;
    private long buyer;

    public TicketDTO() {
    }
}
