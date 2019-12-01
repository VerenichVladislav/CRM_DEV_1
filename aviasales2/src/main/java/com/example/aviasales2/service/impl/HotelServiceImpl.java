package com.example.aviasales2.service.impl;

import com.example.aviasales2.config.filterConfig.HotelFilter;
import com.example.aviasales2.entity.HotelConvenience;
import com.example.aviasales2.entity.QHotel;
import com.example.aviasales2.entity.QRoom;
import com.example.aviasales2.entity.transferObjects.HotelDTO;
import com.example.aviasales2.repository.HotelRepository;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.service.HotelService;
import com.querydsl.core.BooleanBuilder;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> findAll(HotelFilter hotelFilter) {
        final QHotel qHotel = QHotel.hotel;
        final QRoom qRoom = QRoom.room;

        BooleanBuilder booleanBuilder = new BooleanBuilder(qHotel.isNotNull());
        if (hotelFilter.getCity() != null) {
            booleanBuilder.and(qHotel.city.cityName.eq(hotelFilter.getCity()));
        }
        if (hotelFilter.getRating() != null) {
            booleanBuilder.and(qHotel.rating.eq(hotelFilter.getRating()));
        }
        if (hotelFilter.getDateFrom() != null) {
            Timestamp firstTimestamp;
            Timestamp secondTimestamp;
            LocalTime localTime = LocalTime.of(0, 0, 0);
            LocalDate localDate = LocalDate.parse(hotelFilter.getDateFrom());
            LocalDateTime localDateTime = localTime.atDate(localDate);
            firstTimestamp = Timestamp.valueOf(localDateTime.plusHours(3));
            secondTimestamp = Timestamp.valueOf(localDateTime.plusHours(26).plusMinutes(59).plusSeconds(59).plusNanos(999999999));

            booleanBuilder.and(qRoom.checkInDate.between(firstTimestamp, secondTimestamp));
        }
        return (List<Hotel>) hotelRepository.findAll(booleanBuilder);
    }

    @Override
    public Hotel findByHotelId(long id) {
        return hotelRepository.findByHotelId(id);
    }

    @Override
    public String findImageByHotelId(long id) {
        Hotel hotel = hotelRepository.findByHotelId(id);
        return hotel.getImage();
    }

    @Override
    public Hotel findByHotelName(String hotelName) {
        return hotelRepository.findByHotelName(hotelName);
    }

    @Override
    public List<Hotel> findHotelsByHotelConveniences(List<String> hotelConveniences, HotelFilter hotelFilter) {
        List<Hotel> hotels = findAll(hotelFilter);
        List<Hotel> goodHotels = new ArrayList<>();

        Map<Hotel, List<String>> enumString = new HashMap<>();
        for (Hotel hotel : hotels) {
            for (HotelConvenience hotelConvenience : hotel.getHotelConveniences()) {
                if (hotelConveniences.contains(hotelConvenience.name())
                        && !goodHotels.contains(hotel)) {
                    goodHotels.add(hotel);
                }
            }
        }

        for (Hotel hotel : goodHotels) {
            List<String> tempName = new ArrayList<>();
            for (HotelConvenience hotelConvenience : hotel.getHotelConveniences()) {
                String name = hotelConvenience.name();
                tempName.add(name);
            }
            enumString.put(hotel, tempName);
        }

        goodHotels.removeIf(hotel -> !enumString.get(hotel).containsAll(hotelConveniences));

        return goodHotels;
    }
}
