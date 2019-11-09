package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

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

    public UserDTO() {
    }
}
