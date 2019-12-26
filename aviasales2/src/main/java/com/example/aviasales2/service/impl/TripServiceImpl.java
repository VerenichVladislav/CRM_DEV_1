package com.example.aviasales2.service.impl;

import com.example.aviasales2.config.filterConfig.TripFilter;
import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.QTrip;
import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.transferObjects.TripDTO;
import com.example.aviasales2.repository.ICityRepository;
import com.example.aviasales2.repository.TransportRepository;
import com.example.aviasales2.repository.TripRepository;
import com.example.aviasales2.service.TripService;
import com.querydsl.core.BooleanBuilder;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final ICityRepository iCityRepository;
    private final TransportRepository transportRepository;
    private final DozerBeanMapper mapper;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, ICityRepository iCityRepository, TransportRepository transportRepository, DozerBeanMapper mapper) {
        this.tripRepository = tripRepository;
        this.iCityRepository = iCityRepository;
        this.transportRepository = transportRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Trip> findAllCnt(TripFilter tripFilter){
        BooleanBuilder builder = tripDatabaseFilter(tripFilter);
        return (List<Trip>) tripRepository.findAll(builder);
    }

    @Override
    public List<Trip> findAll(TripFilter tripFilter, Integer pageNo, Integer pageSize, String sortBy) {
        BooleanBuilder builder = tripDatabaseFilter(tripFilter);

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Trip> pagedResult = tripRepository.findAll(builder, paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    private BooleanBuilder tripDatabaseFilter(TripFilter tripFilter){
        final QTrip qTrip = QTrip.trip;

        BooleanBuilder builder = new BooleanBuilder(qTrip.isNotNull());
        if (tripFilter.getCityFrom() != null) {
            builder.and(qTrip.cityFrom.cityName.eq(tripFilter.getCityFrom()));
        }
        if (tripFilter.getCityDest() != null) {
            builder.and(qTrip.cityDest.cityName.eq(tripFilter.getCityDest()));
        }
        if (tripFilter.getDateFrom() != null) {
            LocalTime localTime = LocalTime.of(0, 0, 0);
            LocalDate localDate = LocalDate.parse(tripFilter.getDateFrom());
            LocalDateTime localDateTime = localTime.atDate(localDate);

            Timestamp startTimestamp = Timestamp.valueOf(localDateTime.plusHours(3));
            Timestamp endTimestamp = Timestamp.valueOf(localDateTime.plusHours(26).plusMinutes(59).plusSeconds(59).plusNanos(999999999));

            builder.and(qTrip.dateFrom.between(startTimestamp, endTimestamp));
        }

        return builder;
    }

    @Transactional
    @Override
    public Trip save(long cityFromId, long cityDestId, long transportId, TripDTO tripDTO) {
        City cityFrom = iCityRepository.findByCityId(cityFromId);
        City cityDest = iCityRepository.findByCityId(cityDestId);
        Transport transport = transportRepository.findByTransportId(transportId);
        Trip trip = mapper.map(tripDTO, Trip.class);
        if (cityFrom != null) {
            trip.setCityFrom(cityFrom);
            cityFrom.getTrip_from().add(trip);
        } else {
            return null;
        }
        if (cityDest != null) {
            trip.setCityDest(cityDest);
            cityDest.getTrip_dest().add(trip);
        } else {
            return null;
        }
        if (transport != null) {
            trip.setTransport(transport);
            transport.getTrips().add(trip);
        } else {
            return null;
        }
        if (trip.getCityDest() == trip.getCityFrom()) {
            return null;
        }
        return tripRepository.save(trip);
    }


    @Override
    public String deleteById(long id) {
        tripRepository.deleteById(id);
        return "Deleted";
    }

    @Transactional
    @Override
    public Trip update(TripDTO tripDTO) {
        return tripRepository.save(mapper.map(tripDTO, Trip.class));
    }

    @Override
    public Trip findById(Long id) {
        return tripRepository.findByTripId(id);
    }


    @Override
    public BigDecimal getPrice(Long tripId) {
        Trip trip = tripRepository.findByTripId(tripId);
        return trip.getPrice();
    }

    @Override
    public int getFullCountSeats(Long tripId) {
        Trip trip = tripRepository.findByTripId(tripId);
        return trip.getFullCountSeats();
    }

    @Override
    public BigDecimal calculateCost(int count, Long tripId) {
        Trip trip = tripRepository.findByTripId(tripId);
        BigDecimal price = trip.getPrice();
        BigDecimal totalCost = new BigDecimal(BigInteger.ZERO, 2);
        BigDecimal itemCost = price.multiply(new BigDecimal(count));
        return totalCost.add(itemCost);
    }

}
