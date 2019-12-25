package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Trip;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends PagingAndSortingRepository <Trip, Long>, QuerydslPredicateExecutor <Trip> {
    Trip findByTripId(Long id);

    void deleteByTripId(Long id);
}
