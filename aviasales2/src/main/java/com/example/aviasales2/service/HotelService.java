package com.example.aviasales2.service;

import com.example.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel save(Hotel hotel);
    void delete(Hotel hotel);
    void deleteById(long id);
    Hotel update(Hotel hotel);
    List<Hotel> findAll();
    Hotel findByHotelId(long id);
    Hotel findByHotelName(String hotelName);
}
