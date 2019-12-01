package com.example.aviasales2.controller;

import com.example.aviasales2.PersonRequest;
import com.example.aviasales2.config.filterConfig.TripFilter;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.transferObjects.TripDTO;
import com.example.aviasales2.service.*;
import com.example.aviasales2.util.TripValidator;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private Validation validation;
    @Autowired
    private SenderService senderService;
    @Autowired
    private DozerBeanMapper mapper;
    @Autowired
    private TripValidator tripValidator;

    @GetMapping("/{id}")
    public TripDTO findById(@PathVariable("id") long id) {
        return mapper.map(tripService.findById(id), TripDTO.class);
    }

    @PostMapping("/city/{city_f_id}/{city_d_id}/transport/{tr_id}")
    public TripDTO save(@PathVariable("city_f_id") long cityFromId,
                       @PathVariable("city_d_id") long cityDestId,
                       @PathVariable("tr_id") long transportId,
                       @RequestBody @Valid TripDTO tripDTO,
                       BindingResult result) {
        if(result.hasErrors()){
            return null;
        }
        return mapper.map(tripService.save(cityFromId, cityDestId, transportId, tripDTO), TripDTO.class);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        tripService.deleteById(id);
        return "Deleted";
    }

    @PutMapping
    public TripDTO update(@RequestBody @Valid TripDTO tripDTO, BindingResult result) {
        Trip oldTrip = tripService.findById(tripDTO.getTripId());
        if (oldTrip != null) {
            tripValidator.validate(tripDTO, result);
            if(result.hasErrors())
            {return null;}
            return mapper.map(tripService.update(tripDTO), TripDTO.class);
        }
        return null;
    }

    @PostMapping
    public List<TripDTO> findAll(@RequestBody TripFilter tripFilter) {
        return tripService.findAll(tripFilter).stream()
                .map(entity -> mapper.map(entity, TripDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @PostMapping("/{user_id}/{trid_id}/buy")
    ResponseEntity<String> buy(@PathVariable("trid_id") long tripId,
                               @PathVariable("user_id") long userId,
                               @RequestBody List<PersonRequest> passengers,
                               @RequestParam int count) {

        if (validation.checkSeats(tripId, count) != 1.0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Not enough seats!");
        }
        if (validation.checkSum(userId, tripId, count) != 1.0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Insufficient funds! Replenish your wallet!");
        }
        BigDecimal totalCost = tripService.calculateCost(count, tripId);
        walletService.pay(userId, totalCost);
        String list = ticketService.save(userId, tripId, count, passengers);
        senderService.buyEmail(userId, tripId, list, count);
        return ResponseEntity.status(HttpStatus.OK)
                .body("You bought " + count + " ticket(s)! Check your email!");
    }
}

