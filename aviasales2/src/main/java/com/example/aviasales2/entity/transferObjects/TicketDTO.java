package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketDTO {
    private Long id;

    private Integer countSets;

    public TicketDTO() {
    }
}
