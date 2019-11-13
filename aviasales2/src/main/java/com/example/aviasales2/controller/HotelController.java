package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.transferObjects.HotelDTO;
import com.example.aviasales2.service.HotelService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private DozerBeanMapper mapper;

    @GetMapping("/{id}")
    public HotelDTO getHotelById(@PathVariable("id") long id) {
        return mapper.map(hotelService.findByHotelId(id),HotelDTO.class);
    }

    @GetMapping("/")
    public HotelDTO getHotelByName(@RequestParam String hotelName) {
        return mapper.map(hotelService.findByHotelName(hotelName), HotelDTO.class);
    }

    @GetMapping
    public List<HotelDTO> getAllHotels() {
        return hotelService.findAll().stream().map(entity -> mapper.map(entity, HotelDTO.class)).collect(Collectors.toList());
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
