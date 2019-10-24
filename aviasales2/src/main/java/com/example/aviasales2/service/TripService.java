package com.example.aviasales2.service;

import com.example.aviasales2.entity.Trip;

import java.util.List;

public interface TripService {
    List<Trip> findAll();
    String  save(Trip trip);
    String deleteById(long id);
    String update(Trip trip);
    Trip findById(long id);

}
