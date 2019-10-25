package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long sum;


    @OneToOne(optional = false, mappedBy = "wallet")
    private User owner;


    public Wallet(){

    }

   @JsonIgnore
    public User getOwner() {
        return owner;
    }
}
