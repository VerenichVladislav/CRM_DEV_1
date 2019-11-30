package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;
@Getter
@Setter
public class TransportDTO {
    public Long transportId;
    @NotBlank(message = "{invalid.name}")
    @Pattern(regexp = "^[0-9а-яА-ЯёЁa-zA-Z0-9\\s]+$", message = "{invalid.name}")
    String name;
    @NotBlank(message = "Bad baggage number!")
    @Pattern(regexp = "[0-9]+$", message = "Bad baggage number!(only positive number)")
    String baggage;
    int company;
    private Set<TripDTO> trips;

    public TransportDTO() {
    }

}
