package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {
    List<Trip> findAll();
    Trip deleteById(long id);
    Trip findById(long id);
}
