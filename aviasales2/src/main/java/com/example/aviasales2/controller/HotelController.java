package com.example.aviasales2.controller;


import com.example.aviasales2.config.filterConfig.HotelFilter;
import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.entity.Room;
import com.example.aviasales2.entity.transferObjects.CityDTO;
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
import java.util.ArrayList;
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
    private WalletService walletService;
    @Autowired
    private RoomService roomService;

    private HotelValidator hotelValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private ICityService cityService;

    @GetMapping("/{id}")
    public HotelDTO getHotelById(@PathVariable("id") long id) {
        return mapper.map(hotelService.findByHotelId(id), HotelDTO.class);
    }

    @GetMapping("/")
    public HotelDTO getHotelByName(@RequestParam String hotelName) {
        return mapper.map(hotelService.findByHotelName(hotelName), HotelDTO.class);
    }

    @GetMapping("/image/{hotelId}")
    public String getHotelImage(@PathVariable long hotelId) {
        return hotelService.findImageByHotelId(hotelId);
    }

    @GetMapping
    public List<HotelDTO> findAll() {
        return hotelService.findAll().stream().map(entity -> mapper.map(entity, HotelDTO.class)).collect(Collectors.toList());
    }

    @PostMapping
    public List<HotelDTO> getAllHotels(@RequestBody HotelFilter hotelFilter) {
        return hotelService.findAll(hotelFilter).stream()
                .map(entity -> mapper.map(entity, HotelDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/byHotelConveniences")
    public List<HotelDTO> findRoomsByRoomConveniences(@RequestParam List<String> hotelConveniences, HotelFilter hotelFilter){
        List<Hotel> sortedHotels = hotelService.findHotelsByHotelConveniences(hotelConveniences, hotelFilter);
        return sortedHotels.stream().map(entity -> mapper.map(entity, HotelDTO.class)).collect(Collectors.toList());
    }

    @PostMapping("/save")
    public String saveHotel(@RequestBody @Valid HotelDTO hotel, BindingResult result) {
        hotelValidator.validate(hotel, result);
        if (result.hasErrors()){
            return String.valueOf(result.getFieldErrors());
        }
        hotelService.save(mapper.map(hotel,Hotel.class));
        return "saved";
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
            reservation.setCheckIn(checkIn);
            reservation.setCheckOut(checkOut);
            reservation.setRoomId(roomId);
            reservation.setHotel(hotel);
            //reservation.setUserId(userId);
            List<Reservation> reservations = hotel.getReservations();
            reservations.add(reservation);
            hotel.setReservations(reservations);
            hotelService.save(hotel);
            //  walletService.pay(userId,room.getDailyCost());

            return ResponseEntity.status(HttpStatus.OK)
                    .body("You reserved hotel ");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Error");

    }
    @GetMapping()
    public List<CityDTO> getCityWithHotels(){
        List<City> cityList =cityService.findAll();
        List<City> ListWithHotel = new ArrayList<City>();

        for (City city:
             cityList) {
            if(!city.getHotels().isEmpty()){
                ListWithHotel.add(city);
            }

        }
        return ListWithHotel.stream().map(entity -> mapper.map(entity, CityDTO.class))
                .collect(Collectors.toList());

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
