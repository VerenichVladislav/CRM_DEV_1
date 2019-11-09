package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    private Integer id;

    private String email;
    private String currency;
    private String role;
    private Integer historyId;
    private String firstName;
    private String lastName;
    private Integer walletId;
    private Integer hashPass;

    public PersonDTO() {
    }
}
