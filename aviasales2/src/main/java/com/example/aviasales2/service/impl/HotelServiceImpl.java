package com.example.aviasales2.service.impl;

import com.example.aviasales2.config.filterConfig.HotelFilter;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.QHotel;
import com.example.aviasales2.entity.QReservation;
import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.repository.HotelRepository;
import com.example.aviasales2.service.HotelService;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

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
    public List<Hotel> findAll(HotelFilter hotelFilter, int page, int pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "hotelId");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        JPAQuery<Hotel> hotelQuery = hotelDatabaseFilter(hotelFilter);

        List<Hotel> hotels = hotelQuery
                .fetch();
        Set<Hotel> result = new HashSet<>(hotels);
        hotels = new ArrayList<>(result);
        hotels.sort((hotelsFirst, hotelsSorted) ->
                (int) (hotelsFirst.getHotelId() - hotelsSorted.getHotelId()));

        long total = hotelQuery.fetchCount();
        hotelQuery.offset(pageable.getOffset());
        hotelQuery.limit(pageable.getPageSize());

        List<Hotel> content = total > pageable.getOffset() ? hotels : Collections.emptyList();

        if (!hotelFilter.getHotelConveniences().isEmpty()) {
            content = content.stream().filter(hotel -> hotel.getHotelConveniences().stream().map(Enum::name).collect(Collectors.toList())
                    .containsAll(hotelFilter.getHotelConveniences()))
                    .collect(Collectors.toList());
        }

        Page<Hotel> hotelsPage = new PageImpl<>(content, pageable, total);

        return hotelsPage.getContent();
    }

    private JPAQuery<Hotel> hotelDatabaseFilter(HotelFilter hotelFilter) {
        final QHotel qHotel = QHotel.hotel;
        final QReservation qReservation = QReservation.reservation;

        JPAQuery<Hotel> hotelQuery = new JPAQuery<>(entityManager);
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
        if (hotelFilter.getDateFrom() != null && hotelFilter.getDateTo() != null) {
            LocalTime localTimeDateFrom = LocalTime.of(0, 0, 0);
            LocalDate localDateFrom = LocalDate.parse(hotelFilter.getDateFrom());
            LocalDateTime localDateFromTime = localTimeDateFrom.atDate(localDateFrom);
            Timestamp timestampDateFrom = Timestamp.valueOf(localDateFromTime.plusHours(3));

            LocalTime localTimeDateTo = LocalTime.of(0, 0, 0);
            LocalDate localDateTo = LocalDate.parse(hotelFilter.getDateTo());
            LocalDateTime localDateToTime = localTimeDateTo.atDate(localDateTo);
            Timestamp timestampDateTo = Timestamp.valueOf(localDateToTime.plusHours(3));

            JPAQuery<Reservation> reservationQuery = new JPAQuery<>(entityManager);

            List<Reservation> reservations = reservationQuery.from(qReservation)
                    .where(qReservation.checkIn.between(timestampDateFrom, timestampDateTo))
                    .fetch();

            BooleanExpression hotelCheckIn = qReservation.checkIn.notBetween(timestampDateFrom, timestampDateTo)
                    .or(qHotel.reservations.isEmpty());

            BooleanExpression hotelRoomsSize = qHotel.rooms.size().gt(reservations.size());

            hotelQuery.where(hotelCheckIn.and(hotelRoomsSize));
        }

        return hotelQuery;
    }

    @Override
    public Hotel findByHotelName(String hotelName) {
        return hotelRepository.findByHotelName(hotelName);
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
}
