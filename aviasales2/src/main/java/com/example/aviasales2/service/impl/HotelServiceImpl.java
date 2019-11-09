package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.transferObjects.HotelDTO;
import com.example.aviasales2.repository.HotelRepository;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.service.HotelService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private DozerBeanMapper mapper;
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
    public List<HotelDTO> findAll() {
        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll();
        return hotels.stream().map(entity -> mapper.map(entity, HotelDTO.class)).collect(Collectors.toList());
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
