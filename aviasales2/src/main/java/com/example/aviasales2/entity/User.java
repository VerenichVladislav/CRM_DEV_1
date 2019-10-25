package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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
    private String firstName;
    @NotNull
    private String lastName;
    private Integer hashPass;



    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id")
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


    public Wallet getWallet() {
        return wallet;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Tour> getTours() {
        return tours;
    }
}
