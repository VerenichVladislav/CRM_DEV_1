package com.example.aviasales2.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List <Comment> comments;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NotBlank
    @Email
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
    private boolean isLocked;
    private String state = "UnConfirmed";
    private String confirmingHash;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_wallet",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "wallet_id"))
    private Wallet wallet = new Wallet();
    @OneToMany(mappedBy = "buyer",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <Ticket> tickets = new ArrayList <>();
    @ManyToMany
    @JoinTable(name = "history",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private List <Tour> tours = new ArrayList <>();
    @OneToMany(mappedBy = "buyer",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <Reservation> reservations = new ArrayList <>();

    public List <String> getRoleList() {
        if (this.role.length() > 0) {
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList <>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
