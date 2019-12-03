package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends CrudRepository <Tour, Long> {
    Tour findByName(String name);

    List <Tour> findAll();

    Tour findByTourId(Long id);

    Tour deleteByTourId(Long id);
}