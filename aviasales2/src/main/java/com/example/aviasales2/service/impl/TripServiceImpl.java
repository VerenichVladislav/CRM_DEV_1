package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.repository.TripRepository;
import com.example.aviasales2.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private TripRepository tripRepository;
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
}
