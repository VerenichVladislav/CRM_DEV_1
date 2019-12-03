package com.example.aviasales2.service;

import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.entity.transferObjects.TourDTO;

import java.util.List;

public interface TourService {
    Tour save(TourDTO tourDTO, long cityId, long companyId, long hotelId);

    Tour update(TourDTO tour);

    List <Tour> findAll();

    Tour findByName(String name);

    Tour findByTourId(Long id);

    Tour deleteById(Long id);
}
