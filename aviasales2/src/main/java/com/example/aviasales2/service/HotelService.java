package com.example.aviasales2.service;

import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.transferObjects.HotelDTO;

import java.util.List;

public interface HotelService {
    Hotel save(Hotel hotel);
    void delete(Hotel hotel);
    void deleteById(long id);
    Hotel update(Hotel hotel);
    List<HotelDTO> findAll();
    Hotel findByHotelId(long id);
    Hotel findByHotelName(String hotelName);
}
