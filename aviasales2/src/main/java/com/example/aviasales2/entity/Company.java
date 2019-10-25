package com.example.aviasales2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long companyId;

    String companyName;

    long commentId;

    int rating;

    int transportCount;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "compRef")
    private Set<Transport> transportId;

    public Company(){}

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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
}
