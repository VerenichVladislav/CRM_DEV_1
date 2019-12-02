package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long userId;

    @Email(message = "bad email")
    @NotBlank(message = "email should be not null")
    private String email;
    private String currency;
    @NotBlank(message = "Role must be not null")
    private String role;
    @NotBlank(message = "Enter your user name!")
    @Size(min = 4, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Bad user name")
    private String userName;
    @NotBlank(message = "First name should be not null")
    @Size( max = 15)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String firstName;
    @NotBlank(message = "Last name should be not null")
    @Size(max = 15)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String lastName;
    @NotBlank
    @Size(min = 4, max = 30)
    @Pattern(regexp = "[^//.]+$")
    private String hashPass;
    private String state = "UnConfirmed";
    private String confirmingHash;
    @Null
    private Long wallet;
    private List<TicketDTO> tickets;
    private List<ReservationDTO> reservations;
    private List<TourDTO> tours;

    public UserDTO() {
    }
}
