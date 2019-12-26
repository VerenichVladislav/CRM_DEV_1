package com.example.aviasales2.service;

import com.example.aviasales2.config.filterConfig.TripFilter;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.transferObjects.TripDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TripService {
    List <Trip> findAll(TripFilter tripFilter, Integer pageNo, Integer pageSize, String sortBy);

    Trip save(long cityFromId, long cityDestId, long transportId, TripDTO tripDTO);

    String deleteById(long id);

    Trip update(TripDTO trip);

    Trip findById(Long id);

    BigDecimal getPrice(Long tripId);

    int getFullCountSeats(Long tripId);

    List<Trip> findAllCnt(TripFilter tripFilter);

    BigDecimal calculateCost(int count, Long tripId);
}
