package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Setter
@Getter
public class TicketDTO {
    private Long id;

    private Integer countSets;

    private Timestamp date;

    public TicketDTO() {
    }
}
