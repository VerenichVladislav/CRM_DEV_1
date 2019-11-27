package com.example.aviasales2.controller;


import com.example.aviasales2.config.filterConfig.HotelFilter;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.entity.Room;
import com.example.aviasales2.entity.transferObjects.HotelDTO;
import com.example.aviasales2.service.*;
import com.example.aviasales2.util.HotelValidator;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping(value = "/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private DozerBeanMapper mapper;
    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelValidator hotelValidator;


    @GetMapping("/{id}")
    public HotelDTO getHotelById(@PathVariable("id") long id) {
        return mapper.map(hotelService.findByHotelId(id), HotelDTO.class);
    }

    @GetMapping("/")
    public HotelDTO getHotelByName(@RequestParam String hotelName) {
        return mapper.map(hotelService.findByHotelName(hotelName), HotelDTO.class);
    }

    @PostMapping
    public List<HotelDTO> getAllHotels(@RequestBody HotelFilter hotelFilter) {
        return hotelService.findAll(hotelFilter).stream()
                .map(entity -> mapper.map(entity, HotelDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public Hotel saveHotel(@RequestBody @Valid HotelDTO hotel, BindingResult result) {
        hotelValidator.validate(hotel, result);
        if (result.hasErrors()){
            return null;
        }
        return hotelService.save(mapper.map(hotel,Hotel.class));
    }

    @Transactional
    @PostMapping("/{user_id}/{hotel_id}/{checkIn}/{checkOut}/{roomId}")
    public ResponseEntity<String> addReservation(@PathVariable("user_id") Long userId,
                                                 @PathVariable("hotel_id") Long hotelId,
                                                 @PathVariable("checkIn") Timestamp checkIn,
                                                 @PathVariable("checkOut") Timestamp checkOut,
                                                 @PathVariable("roomId") Long roomId
    ) {
        Hotel hotel = hotelService.findByHotelId(hotelId);
        Room room = roomService.findByRoomId(roomId);
        if (hotel != null) {
            Reservation reservation = new Reservation();
            reservation.setСheckIn(checkIn);
            reservation.setСheckOut(checkOut);
            reservation.setRoomId(roomId);
            reservation.setHotel(hotel);
            //reservation.setUserId(userId);
            List<Reservation> reservations = hotel.getReservations();
            reservations.add(reservation);
            hotel.setReservations(reservations);
            hotelService.save(hotel);
            //  walletService.pay(userId,room.getDailyCost());

            return ResponseEntity.status(HttpStatus.OK)
                    .body("You reserv hotel ");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Error");

    }

    @PutMapping()
    public Hotel updateHotel(@RequestBody Hotel hotel) {
        Hotel old = hotelService.findByHotelId(hotel.getHotelId());
        if (old != null)
            return hotelService.save(hotel);
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable("id") long id) {
        hotelService.deleteById(id);
    }

}
