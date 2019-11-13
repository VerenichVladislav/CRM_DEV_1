package com.example.aviasales2.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
    public void setState(String state) {
        this.state = state;
    }

    public void setConfirmingHash(String confirmingHash) {
        this.confirmingHash = confirmingHash;
    }

    public String getConfirmingHash() {
        return confirmingHash;
    }

    public String getState() {
        return state;
    }
    public List<String> getRoleList(){
        if(this.role.length() > 0){
            return Arrays.asList(this.role.split(","));
        }
        return  new ArrayList<>();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashPass() {
        return hashPass;
    }

    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
