package com.example.aviasales2.service;

import com.example.aviasales2.config.filterConfig.TripFilter;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.transferObjects.TripDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TripService {
    List<Trip> findAll(TripFilter tripFilter);
    String  save(long cityFromId, long cityDestId, long transportId, Trip trip);
    String deleteById(long id);
    String update(Trip trip);
    Trip findById(long id);
    BigDecimal getPrice (long tripId);
    int getFullCountSeats(long tripId);
    BigDecimal calculateCost(int count, long tripId);
}
