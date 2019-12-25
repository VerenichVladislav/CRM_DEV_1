package com.example.aviasales2.controller;


import com.example.aviasales2.config.filterConfig.HotelFilter;
import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.entity.Room;
import com.example.aviasales2.entity.transferObjects.CityDTO;
import com.example.aviasales2.entity.transferObjects.HotelDTO;
import com.example.aviasales2.entity.transferObjects.ReservationDTO;
import com.example.aviasales2.exception.GlobalBadRequestException;
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

    private final HotelService hotelService;
    private final DozerBeanMapper mapper;
    private final WalletService walletService;
    private final RoomService roomService;
    private final UserService userService;
    private final ICityService cityService;
    private HotelValidator hotelValidator;

    @Autowired
    public HotelController(HotelService hotelService, DozerBeanMapper mapper, WalletService walletService, RoomService roomService, UserService userService, ICityService cityService, HotelValidator hotelValidator) {
        this.hotelService = hotelService;
        this.mapper = mapper;
        this.walletService = walletService;
        this.roomService = roomService;
        this.userService = userService;
        this.cityService = cityService;
        this.hotelValidator = hotelValidator;
    }

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

    @PostMapping
    public List <HotelDTO> getAllHotels(@RequestBody HotelFilter hotelFilter,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        return hotelService.findAll(hotelFilter, page, pageSize).stream()
                .map(entity -> mapper.map(entity, HotelDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public ResponseEntity<HotelDTO> saveHotel(@RequestBody @Valid HotelDTO data, BindingResult result) {
        hotelValidator.validate(data, result);
        if (result.hasErrors()){
            throw new GlobalBadRequestException(result);
        }
        Hotel hotel = mapper.map(data, Hotel.class);
        City city = cityService.findByCityId(data.getCityId());
        hotel.setCity(cityService.findByCityId(data.getCityId()));
        city.getHotels().add(hotel);
        hotelService.save(hotel);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/{user_id}/{hotel_id}/{checkIn}/{checkOut}/{roomId}")
    public ResponseEntity <ReservationDTO> addReservation(@PathVariable("user_id") Long userId,
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
            reservation.setBuyer(userService.findById(userId));
            List <Reservation> reservations = hotel.getReservations();
            reservations.add(reservation);
            hotel.setReservations(reservations);
            hotelService.save(hotel);
            //  walletService.pay(userId,room.getDailyCost());
            ReservationDTO body = mapper.map(reservation, ReservationDTO.class);
            return new ResponseEntity <>(body, HttpStatus.OK);
        }
        throw new RuntimeException();

    }

    @GetMapping()
    public List <CityDTO> getCityWithHotels() {
        List <City> cityList = cityService.findAll();
        List <City> ListWithHotel = new ArrayList <City>();

        for (City city :
                cityList) {
            if (!city.getHotels().isEmpty()) {
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
    public void deleteHotel(@PathVariable("id") Long id) {
        hotelService.deleteById(id);
    }

}
