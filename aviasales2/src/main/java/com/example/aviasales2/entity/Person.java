package com.example.aviasales2.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
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

    public Person(){}

    public Person(String email, String currency, String role, Integer historyId, String firstName, String lastName, Integer walletId, Integer hashPass) {
        this.email = email;
        this.currency = currency;
        this.role = role;
        this.historyId = historyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.walletId = walletId;
        this.hashPass = hashPass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getHashPass() {
        return hashPass;
    }

    public void setHashPass(Integer hashPass) {
        this.hashPass = hashPass;
    }
}
