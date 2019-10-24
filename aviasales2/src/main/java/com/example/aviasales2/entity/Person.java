package com.example.aviasales2.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String email;
    private String currency;
    private String role;
    private Integer historyId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private Integer walletId;
    private Integer hashPass;

}
