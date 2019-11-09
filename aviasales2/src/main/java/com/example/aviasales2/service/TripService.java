package com.example.aviasales2.service;

import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.transferObjects.TripDTO;

import java.util.List;

public interface TripService {
    List<TripDTO> findAll(String cityFrom, String cityDest, String date);
    String  save(long cityFromId, long cityDestId, long transportId, Trip trip);
    String deleteById(long id);
    String update(Trip trip);
    Trip findById(long id);


}
