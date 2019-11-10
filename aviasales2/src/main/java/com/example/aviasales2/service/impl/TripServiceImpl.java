package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.QTrip;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.repository.ICityRepository;
import com.example.aviasales2.repository.TripRepository;
import com.example.aviasales2.service.TripService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    ICityRepository iCityRepository;
    private final QTrip qTrip = QTrip.trip;
    @Override
    public List<Trip> findAll(String cityFrom, String cityDest, String date){
        BooleanBuilder builder = new BooleanBuilder(qTrip.isNotNull());
        if(cityFrom != null){
            builder.and(qTrip.cityFrom.eq(iCityRepository.findByCityName(cityFrom)));
        }
        if (cityDest != null){
            builder.and(qTrip.cityDest.eq(iCityRepository.findByCityName(cityDest)));
        }
        if (date != null){

            builder.and(qTrip.dateFrom.eq(Timestamp.valueOf(date)));
        }
        return (List<Trip>) tripRepository.findAll(builder);

    }

    @Override
    public String save(Trip trip){tripRepository.save(trip); return "Saved";}

    @Override
    public String deleteById(long id){tripRepository.deleteById(id); return "Deleted";}

    @Override
    public String update(Trip trip){tripRepository.save(trip); return "Updated";}

    @Override
    public Trip findById(long id){return tripRepository.findById(id);}


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
