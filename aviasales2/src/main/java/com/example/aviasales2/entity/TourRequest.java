package com.example.aviasales2.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
public class TourRequest {
    Long userId;
    String firstName;
    String lastName;
    List<Long> tripIdList;

    public Long getUserId() {
        return userId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<Long> getTripIdList() {
        return tripIdList;
    }
}
