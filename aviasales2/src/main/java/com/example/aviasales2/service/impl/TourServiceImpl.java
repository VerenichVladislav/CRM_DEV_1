package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.repository.TourRepository;
import com.example.aviasales2.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    TourRepository tourRepository;

    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public List <Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public Tour findByName(String name) {
        return tourRepository.findByName(name);
    }

    @Override
    public Tour deleteById(long id) {
        return tourRepository.deleteById(id);
    }

    @Override
    public void update(Tour tour) {
        tourRepository.save(tour);
    }

    @Override
    public Tour findByTourId(long id) {
        Tour obj = tourRepository.findById(id).get();
        return obj;
    }
}
