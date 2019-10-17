package com.example.aviasales2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue
    long companyId;

    String companyName;

    long transportId;

    long commentId;

    int rating;

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

    public long getTransportId() {
        return transportId;
    }

    public void setTransportId(long transportId) {
        this.transportId = transportId;
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
}
