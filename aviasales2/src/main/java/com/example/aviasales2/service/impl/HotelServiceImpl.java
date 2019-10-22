package com.example.aviasales2.service.impl;

import com.example.aviasales2.repository.HotelRepository;
import com.example.entity.Hotel;
import com.example.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    @Override
    public void deleteById(long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return null;
    }

    @Override
    public List<Hotel> findAll() {
        return (List<Hotel>) hotelRepository.findAll();
    }

    @Override
    public Hotel findByHotelId(long id) {
        return hotelRepository.findByHotelId(id);
    }

    @Override
    public Hotel findByHotelName(String hotelName) {
        return hotelRepository.findByHotelName(hotelName);
    }
}
