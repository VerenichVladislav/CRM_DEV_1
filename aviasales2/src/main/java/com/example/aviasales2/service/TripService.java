package com.example.aviasales2.service;

import com.example.aviasales2.entity.Trip;

import java.sql.Timestamp;
import java.util.List;

public interface TripService {
    List<Trip> findAll();
    String  save(Trip trip);
    String deleteById(long id);
    String update(Trip trip);
    Trip findById(long id);
    List<Trip> findAllByCityFromAndCityDest(String cityFrom, String cityDest);
    List<Trip> findAllByCityFrom(String cityFrom);
    List<Trip> findAllByCityDest(String cityDest);
    List<Trip> findAllByDateFrom(Timestamp date);
    Trip buy(long trip_id,long user_id,long count);
}
