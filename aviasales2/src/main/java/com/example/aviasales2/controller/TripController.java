package com.example.aviasales2.controller;

import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.service.ICityService;
import com.example.aviasales2.service.TransportService;
import com.example.aviasales2.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private ICityService iCityService;
    @Autowired
    private TransportService transportService;


    @GetMapping("/{id}")
    public Trip findById(@PathVariable("id") long id){return tripService.findById(id);}

    @PostMapping("/city/{city_f_id}/{city_d_id}/transport/{tr_id}")
    public String save(@PathVariable("city_f_id") long city_from_id,
                        @PathVariable("city_d_id") long city_dest_id,
                        @PathVariable("tr_id") long transport_id,
                        @RequestBody Trip trip){
        Optional<City> cityFrom = iCityService.findById(city_from_id);
        Optional<City> cityDest = iCityService.findById(city_dest_id);
        Optional<Transport> transport = transportService.findById(transport_id);
        if(cityFrom.isPresent()){
            trip.setCityFrom(cityFrom.get());
            cityFrom.get().getTrip_from().add(trip);
        }
        else {return "City_From does not exist!";}
        if (cityDest.isPresent()){
            trip.setCityDest(cityDest.get());
            cityDest.get().getTrip_dest().add(trip);
        }
        else {return "City_Dest does not exist!";}
        if (transport.isPresent()){
            trip.setTransport(transport.get());
            transport.get().getTrips().add(trip);
        }
        else {return "Transport does not exist";}


        return tripService.save(trip);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){tripService.deleteById(id); return "Deleted";}

    @PutMapping
    public String update(@RequestBody Trip trip){
        Trip oldTrip = tripService.findById(trip.getId());
        if (oldTrip != null){
            tripService.save(trip);
            return "Updated";
        }
        return "Trip does not exist";
    }

    @GetMapping("/")
    public List<Trip> findByCity(@RequestParam(name = "cityFrom", required = false)String cityFrom,
                                  @RequestParam(name = "cityDest", required = false)String cityDest,
                                  @RequestParam(name = "date", required = false) String date
    ){
        return tripService.findAll(cityFrom, cityDest, date);
    }



}
