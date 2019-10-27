package com.example.aviasales2.repository;

import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {
    List<Trip> findAll();
    Trip deleteById(long id);
    Trip findById(long id);
    List<Trip> findAllByCityFromAndCityDest(City cityFrom, City cityDest);
    List<Trip> findAllByCityFrom(City cityFrom);
    List<Trip> findAllByCityDest(City cityDest);
    List<Trip> findAllByDateFrom(Timestamp date);
}
