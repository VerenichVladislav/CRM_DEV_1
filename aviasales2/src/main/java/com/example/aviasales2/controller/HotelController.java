package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable("id") long id) {
        return hotelService.findByHotelId(id);
    }

    @GetMapping("/")
    public Hotel getHotelByName(@RequestParam String hotelName) {
        return hotelService.findByHotelName(hotelName);
    }

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.findAll();
    }

    @PostMapping()
    public Hotel saveHotel(@RequestBody Hotel hotel) {
        return hotelService.save(hotel);
    }

    @PutMapping()
    public Hotel updateHotel(@RequestBody Hotel hotel) {
        Hotel old = hotelService.findByHotelId(hotel.getHotelId());
        if(old != null)
            return hotelService.save(hotel);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable("id") long id){
        hotelService.deleteById(id);
    }

}
