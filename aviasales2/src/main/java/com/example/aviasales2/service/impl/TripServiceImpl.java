package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.*;
import com.example.aviasales2.repository.ICityRepository;
import com.example.aviasales2.repository.TripRepository;
import com.example.aviasales2.repository.WalletRepository;
import com.example.aviasales2.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private ICityRepository iCityRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Override
    public List<Trip> findAll(){return tripRepository.findAll();}

    @Override
    public String save(Trip trip){tripRepository.save(trip); return "Saved";}

    @Override
    public String deleteById(long id){tripRepository.deleteById(id); return "Deleted";}

    @Override
    public String update(Trip trip){tripRepository.save(trip); return "Updated";}

    @Override
    public Trip findById(long id){return tripRepository.findById(id);}

    @Override
    public List<Trip> findAllByCityFromAndCityDest(String cityFrom, String cityDest){
        City city1 = iCityRepository.findByCityName(cityFrom);
        City city2 = iCityRepository.findByCityName(cityDest);
        return tripRepository.findAllByCityFromAndCityDest(city1, city2);
    }
    @Override
    public List<Trip> findAllByCityFrom(String cityFrom){
        City city = iCityRepository.findByCityName(cityFrom);
        return tripRepository.findAllByCityFrom(city);
    }
    @Override
    public List<Trip> findAllByCityDest(String cityDest){
        City city = iCityRepository.findByCityName(cityDest);
        return tripRepository.findAllByCityDest(city);
    }
    @Override
    public List<Trip> findAllByDateFrom(Timestamp date){
        return tripRepository.findAllByDateFrom(date);
    }

    @Override
    public Trip buy(long id,int count) {
        Trip trip = tripRepository.findById(id);
        Wallet wallet = walletRepository.findById(id);
        double sum = wallet.getSum();
        double price = trip.getPrice();

        if(sum>=price){
            sum-=price;
            Ticket ticket = new Ticket();
        }
        for(int i=0;i<count;i++){
            Ticket T= new Ticket(i);
        }
        return null;
    }

}
