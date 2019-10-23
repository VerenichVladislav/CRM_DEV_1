package com.example.aviasales2.entity;

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
public class Company {
    @Id
    @GeneratedValue
    long companyId;

    String companyName;

    long commentId;

    int rating;

    int transportCount;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonManagedReference
    private Set<Transport> transportId;

    public Company(){}

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setTransportId(Set<Transport> transportId) {
        this.transportId = transportId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public long getCommentId() {
        return commentId;
    }

    public int getRating() {
        return rating;
    }

    public Set<Transport> getTransportId() {
        return transportId;
    }

    public int getTransportCount() {
        return transportCount;
    }

    public void setTransportCount(int transport_count) {
        this.transportCount = transport_count;
    }
}
