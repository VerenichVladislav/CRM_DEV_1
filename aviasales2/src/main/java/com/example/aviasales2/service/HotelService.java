package com.example.aviasales2.service;

import com.example.aviasales2.config.filterConfig.HotelFilter;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.transferObjects.HotelDTO;

import java.util.List;

public interface HotelService {
    Hotel save(Hotel hotel);
    void delete(Hotel hotel);
    void deleteById(long id);
    Hotel update(Hotel hotel);
    List<Hotel> findAll();
    List<Hotel> findAll(HotelFilter hotelFilter);
    Hotel findByHotelId(long id);
    Hotel findByHotelName(String hotelName);
    List<Hotel> findHotelsByHotelConveniences(List<String> hotelConveniences, HotelFilter hotelFilter);
}
