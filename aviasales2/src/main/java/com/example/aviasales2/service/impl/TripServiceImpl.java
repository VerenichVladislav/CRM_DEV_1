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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private ICityRepository iCityRepository;
    @Autowired
    private TransportRepository transportRepository;

    @Override
    public List<Trip> findAll(TripFilter tripFilter) {
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
            Timestamp timestamp;
            Timestamp timestamp1;
            timestamp = Timestamp.valueOf(localDateTime.plusHours(3));
            timestamp1 = Timestamp.valueOf(localDateTime.plusHours(26).plusMinutes(59).plusSeconds(59).plusNanos(999999999));

            builder.and(qTrip.dateFrom.between(timestamp, timestamp1));
        }
        return (List<Trip>) tripRepository.findAll(builder);

    }

    @Override
    public String save(long cityFromId, long cityDestId, long transportId, Trip trip) {
        Optional<City> cityFrom = iCityRepository.findById(cityFromId);
        Optional<City> cityDest = iCityRepository.findById(cityDestId);
        Optional<Transport> transport = transportRepository.findById(transportId);
        if (cityFrom.isPresent()) {
            trip.setCityFrom(cityFrom.get());
            cityFrom.get().getTrip_from().add(trip);
        } else {
            return "City_From does not exist!";
        }
        if (cityDest.isPresent()) {
            trip.setCityDest(cityDest.get());
            cityDest.get().getTrip_dest().add(trip);
        } else {
            return "City_Dest does not exist!";
        }
        if (transport.isPresent()) {
            trip.setTransport(transport.get());
            transport.get().getTrips().add(trip);
        } else {
            return "Transport does not exist";
        }
        tripRepository.save(trip);
        return "Saved";
    }

    @Override
    public String deleteById(long id) {
        tripRepository.deleteById(id);
        return "Deleted";
    }

    @Override
    public String update(Trip trip) {
        tripRepository.save(trip);
        return "Updated";
    }

    @Override
    public Trip findById(long id) {
        return tripRepository.findById(id);
    }


    @Override
    public BigDecimal getPrice(long tripId) {
        Trip trip = tripRepository.findById(tripId);
        BigDecimal price = trip.getPrice();
        return price;
    }

    @Override
    public int getFullCountSeats(long tripId) {
        Trip trip = tripRepository.findById(tripId);
        int fullCountSeats = trip.getFullCountSeats();
        return fullCountSeats;
    }

    @Override
    public BigDecimal calculateCost(int count, long tripId) {
        Trip trip = tripRepository.findById(tripId);
        BigDecimal price = trip.getPrice();
        BigDecimal totalCost = new BigDecimal(BigInteger.ZERO, 2);
        BigDecimal itemCost = price.multiply(new BigDecimal(count));
        return totalCost.add(itemCost);
    }

}
