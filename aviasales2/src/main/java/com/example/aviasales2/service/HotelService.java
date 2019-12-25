package com.example.aviasales2.service;

import com.example.aviasales2.config.filterConfig.HotelFilter;
import com.example.aviasales2.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel save(Hotel hotel);

    void delete(Hotel hotel);

    void deleteById(long id);

    Hotel update(Hotel hotel);

    List <Hotel> findAll();

    List <Hotel> findAll(HotelFilter hotelFilter, int page, int pageSize);

    Hotel findByHotelId(long id);

    String findImageByHotelId(long id);

    Hotel findByHotelName(String hotelName);
}
