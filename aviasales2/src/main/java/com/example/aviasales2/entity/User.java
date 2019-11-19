package com.example.aviasales2.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_t")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

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
    private Wallet wallet = new Wallet();

    @ManyToMany
    @JoinTable (name="active_booking",
            joinColumns=@JoinColumn (name="user_id"),
            inverseJoinColumns=@JoinColumn(name="ticket_id"))
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToMany
    @JoinTable (name="history",
            joinColumns=@JoinColumn (name="user_id"),
            inverseJoinColumns=@JoinColumn(name="tour_id"))
    private List<Tour> tours = new ArrayList<>();

    public List<String> getRoleList(){
        if(this.role.length() > 0){
            return Arrays.asList(this.role.split(","));
        }
        return  new ArrayList<>();
    }
}
