package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long userId;

    private String email;
    private String currency;
    private String role;
    private String userName;
    private String firstName;
    private String lastName;
    private String hashPass;
    private String state = "UnConfirmed";
    private String confirmingHash;
    private long wallet;
    private List<TicketDTO> tickets;
    private List<TourDTO> tours;

    public UserDTO() {
    }
}
