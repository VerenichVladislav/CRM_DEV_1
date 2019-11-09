package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.transferObjects.TripDTO;
import com.example.aviasales2.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;

    @GetMapping("/{id}")
    public Trip findById(@PathVariable("id") long id){return tripService.findById(id);}
    @PostMapping("/city/{city_f_id}/{city_d_id}/transport/{tr_id}")
    public String save(@PathVariable("city_f_id") long cityFromId,
                        @PathVariable("city_d_id") long cityDestId,
                        @PathVariable("tr_id") long transportId,
                        @RequestBody Trip trip){
        return tripService.save(cityFromId, cityDestId, transportId, trip);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){tripService.deleteById(id); return "Deleted";}

    @PutMapping
    public String update(@RequestBody Trip trip){
        Trip oldTrip = tripService.findById(trip.getId());
        if (oldTrip != null){
            return tripService.update(trip);
        }
        return "Trip does not exist";
    }

    @GetMapping("/")
    public List<TripDTO> findByCity(@RequestParam(name = "cityFrom", required = false)String cityFrom,
                                    @RequestParam(name = "cityDest", required = false)String cityDest,
                                    @RequestParam(name = "date", required = false) String date
    ){
        return tripService.findAll(cityFrom, cityDest, date);
    }



}
