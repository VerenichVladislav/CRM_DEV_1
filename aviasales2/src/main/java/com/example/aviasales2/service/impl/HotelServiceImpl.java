package com.example.aviasales2.service.impl;

import com.example.aviasales2.config.filterConfig.HotelFilter;
import com.example.aviasales2.entity.QHotel;
import com.example.aviasales2.entity.transferObjects.HotelDTO;
import com.example.aviasales2.repository.HotelRepository;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.service.HotelService;
import com.querydsl.core.BooleanBuilder;
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
    public List<Hotel> findAll(HotelFilter hotelFilter) {
        final QHotel qHotel = QHotel.hotel;

        BooleanBuilder booleanBuilder = new BooleanBuilder(qHotel.isNotNull());
        if (hotelFilter.getCity() != null) {
            booleanBuilder.and(qHotel.city.eq(hotelFilter.getCity()));
        }
        if (hotelFilter.getRating() != null) {
            booleanBuilder.and(qHotel.rating.eq(hotelFilter.getRating()));
        }
        return (List<Hotel>) hotelRepository.findAll(booleanBuilder);
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
