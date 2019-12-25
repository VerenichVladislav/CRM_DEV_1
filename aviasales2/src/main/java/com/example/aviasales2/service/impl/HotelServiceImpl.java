package com.example.aviasales2.service.impl;

import com.example.aviasales2.config.filterConfig.HotelFilter;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.QHotel;
import com.example.aviasales2.entity.QReservation;
import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.repository.HotelRepository;
import com.example.aviasales2.repository.TourRepository;
import com.example.aviasales2.service.HotelService;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final CompanyRepository companyRepository;
    private final TourRepository tourRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, CompanyRepository companyRepository, TourRepository tourRepository) {
        this.hotelRepository = hotelRepository;
        this.companyRepository = companyRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    @Transactional
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
    public List <Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public List <Hotel> findAll(HotelFilter hotelFilter, int page) {
        Sort sort = new Sort(Sort.Direction.ASC, "hotelId");
        Pageable pageable = PageRequest.of(page, 10, sort);
        final QHotel qHotel = QHotel.hotel;
        final QReservation qReservation = QReservation.reservation;
        JPAQuery <Hotel> hotelQuery = new JPAQuery <>(entityManager);
        List <Hotel> hotels;
        hotelQuery.from(qHotel)
                .leftJoin(qHotel.reservations, qReservation)
                .on(qReservation.hotel.hotelId.eq(qHotel.hotelId))
                .where(qHotel.isNotNull());
        if (hotelFilter.getCity() != null) {
            BooleanExpression hotelCityName = qHotel.city.cityName.eq(hotelFilter.getCity());
            hotelQuery.where(hotelCityName);
        }
        if (hotelFilter.getRating() != null) {
            BooleanExpression hotelRating = qHotel.rating.eq(hotelFilter.getRating());
            hotelQuery.where(hotelRating);
        }
        if (hotelFilter.getDateFrom() != null) {
            Timestamp firstTimestamp;
            Timestamp secondTimestamp;
            LocalTime localTime = LocalTime.of(0, 0, 0);
            LocalDate localDate = LocalDate.parse(hotelFilter.getDateFrom());
            LocalDateTime localDateTime = localTime.atDate(localDate);
            firstTimestamp = Timestamp.valueOf(localDateTime.plusHours(3));
            secondTimestamp = Timestamp.valueOf(localDateTime.plusHours(26).plusMinutes(59).plusSeconds(59).plusNanos(999999999));
            JPAQuery <Reservation> reservationQuery = new JPAQuery <>(entityManager);
            List <Reservation> reservations = reservationQuery.from(qReservation).where(qReservation.checkIn.between(firstTimestamp, secondTimestamp)).fetch();
            BooleanExpression hotelCheckIn = qReservation.checkIn.notBetween(firstTimestamp, secondTimestamp)
                    .or(qHotel.reservations.isEmpty());
            BooleanExpression hotelRoomsSize = qHotel.rooms.size().gt(reservations.size());
            hotelQuery.where(hotelCheckIn.and(hotelRoomsSize));
        }
        //hotelQuery.where()
        hotels = hotelQuery
                .fetch();
        Set <Hotel> result = new HashSet <>(hotels);
        hotels = new ArrayList <>(result);
        hotels.sort((hotelsFirst, hotelsSorted) ->
                (int) (hotelsFirst.getHotelId() - hotelsSorted.getHotelId()));

        long total = hotelQuery.fetchCount();

        hotelQuery.offset(pageable.getOffset());
        hotelQuery.limit(pageable.getPageSize());

        List <Hotel> content = total > pageable.getOffset() ? hotels : Collections.emptyList();

        Page <Hotel> hotelsPage = new PageImpl <>(content, pageable, total);
//        PageUtils pageUtils = new PageUtils();
//        int current = hotelsPage.getNumber() + 1;
//        int begin = pageUtils.pagingBegin(current);
//        int end = pageUtils.pagingEnd(hmCattlePage.getTotalPages());
//        Page<Hotel> hotelsPage = hotelRepository.findAll(hotelQuery, pageable);
//        return hotelsPage.getContent();
        return hotelsPage.getContent();
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
    public List <Hotel> findHotelsByHotelConveniences(List <String> hotelConveniences, HotelFilter hotelFilter, int page) {
        return findAll(hotelFilter, page).stream().filter(hotel -> hotel.getHotelConveniences().stream().map(Enum::name).collect(Collectors.toList())
                .containsAll(hotelConveniences))
                .collect(Collectors.toList());
    }
}
