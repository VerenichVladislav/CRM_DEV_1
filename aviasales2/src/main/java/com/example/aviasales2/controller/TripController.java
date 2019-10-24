package com.example.aviasales2.controller;

import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.repository.ICityRepository;
import com.example.aviasales2.repository.TransportRepository;
import com.example.aviasales2.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/trip")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private ICityRepository iCityRepository;
    @Autowired
    private TransportRepository transportRepository;

    @GetMapping
    private List<Trip> findAll(){return tripService.findAll();}

    @GetMapping("/getById/{id}")
    private Trip findById(@PathVariable("id") long id){return tripService.findById(id);}

    @PostMapping("/save/{city_f_id}/{city_d_id}/{tr_id}")
    private String save(@PathVariable("city_f_id") long city_from_id, @PathVariable("city_d_id") long city_dest_id, @PathVariable("tr_id") long transport_id, @RequestBody Trip trip){
        Optional<City> city_from =iCityRepository.findById(city_from_id);
        Optional<City> city_dest = iCityRepository.findById(city_dest_id);
        Optional<Transport> transport = transportRepository.findById(transport_id);
        if(city_from.isPresent()){
            trip.setCity_from(city_from.get());
            city_from.get().getTrip_from().add(trip);
        }
        else {return "City_From does not exist!";}
        if (city_dest.isPresent()){
            trip.setCity_dest(city_dest.get());
            city_dest.get().getTrip_dest().add(trip);
        }
        else {return "City_Dest does not exist!";}
        if (transport.isPresent()){
            trip.setTransport(transport.get());
            transport.get().getTrips().add(trip);
        }
        else {return "Transport does not exist";}


        return tripService.save(trip);
    }

    @GetMapping("/delete")
    private String delete(@RequestParam("id") long id){tripService.deleteById(id); return "Deleted";}

    @PutMapping("/update")
    private String update(@RequestBody Trip trip){
        Trip oldTrip = tripService.findById(trip.getId());
        if (oldTrip != null){
            tripService.save(trip);
            return "Updated";
        }
        return "Trip does not exist";
    }



}
