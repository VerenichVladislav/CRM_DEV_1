package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "company name is null")
    private String companyName;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comments> comments;

    @OneToMany(mappedBy = "company",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Tour> tours;

    private String rating;

    private int transportCount;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transport> transportId;

    public Company(){}

    public String getCompanyName() {
        return companyName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getTransportCount() {
        return transportCount;
    }

    public void setTransportCount(int transportCount) {
        this.transportCount = transportCount;
    }

    public Set<Transport> getTransportId() {
        return transportId;
    }

    public void setTransportId(Set<Transport> transportId) {
        this.transportId = transportId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;


    }
}
