package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer countSets;

    @ManyToMany
    @JoinTable (name="active_booking",
            joinColumns=@JoinColumn (name="ticket_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> owners;

    public Ticket (){

    }

    @JsonIgnore
    public List<User> getOwners() {
        return owners;
    }
}
