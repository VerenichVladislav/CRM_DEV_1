package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long companyId;

    private String companyName;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comments> comments;

    @OneToMany(mappedBy = "company",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private Set<Tour> tours;

    private int rating;

    private int transportCount;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transport> transportId;

    public Company(){}


}
