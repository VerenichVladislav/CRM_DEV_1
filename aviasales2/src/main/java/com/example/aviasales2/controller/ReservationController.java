package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Reservation;
import com.example.aviasales2.entity.User;
import com.example.aviasales2.entity.transferObjects.ReservationDTO;
import com.example.aviasales2.service.ReservationService;
import com.example.aviasales2.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {
    @Autowired
    UserService userService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    DozerBeanMapper mapper;


    @GetMapping("/buyer/{buyer_id}")
    public List<ReservationDTO> findAllByBuyerId(@PathVariable("buyer_id") Long id) {
        User user = userService.findById(id);
        return reservationService.findAllByBuyer(user).stream()
                .map(entity -> mapper.map(entity, ReservationDTO.class))
                .collect(Collectors.toList());
    }
}
