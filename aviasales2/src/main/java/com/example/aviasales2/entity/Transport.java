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
    @GeneratedValue
    public long id;
    String name;
    int baggage;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference(value = "compRef")
    @JoinColumn(name = "company_id")
    Company company;
    @OneToMany(mappedBy = "transport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Trip> trips;

    public Transport(){

    }
    public Long getId(){
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBaggage(int baggage) {
        this.baggage = baggage;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public int getBaggage() {
        return baggage;
    }

    public Company getCompany() {
        return company;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }
}