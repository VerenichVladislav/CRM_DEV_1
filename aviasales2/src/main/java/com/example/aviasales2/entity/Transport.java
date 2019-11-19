package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    private String name;
    private int baggage;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "transport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Trip> trips;


    public Transport(){

    }
}