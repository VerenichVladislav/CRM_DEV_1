package com.example.aviasales2.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user_t")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String email;
    private String currency;
    private String role;
    @NotNull
    private String userName;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String hashPass;


    private String state = "UnConfirmed";
    private String confirmingHash;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_wallet",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="wallet_id"))
    private Wallet wallet;

    @ManyToMany
    @JoinTable (name="active_booking",
            joinColumns=@JoinColumn (name="user_id"),
            inverseJoinColumns=@JoinColumn(name="ticket_id"))
    private List<Ticket> tickets;

    @ManyToMany
    @JoinTable (name="history",
            joinColumns=@JoinColumn (name="user_id"),
            inverseJoinColumns=@JoinColumn(name="tour_id"))
    private List<Tour> tours;

    public User() {
    }

    public List<String> getRoleList(){
        if(this.role.length() > 0){
            return Arrays.asList(this.role.split(","));
        }
        return  new ArrayList<>();
    }
}
