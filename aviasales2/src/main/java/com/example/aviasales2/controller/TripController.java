package com.example.aviasales2.controller;

import com.example.aviasales2.PersonRequest;
import com.example.aviasales2.config.filterConfig.TripFilter;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.transferObjects.TripDTO;
import com.example.aviasales2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private IWalletService walletService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private Validation validation;
    @Autowired
    private SenderService senderService;

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

    @PostMapping
    public List<TripDTO> findAll(@RequestBody TripFilter tripFilter){
        return tripService.findAll(tripFilter);
    }

    @PostMapping("/{user_id}/{trid_id}/buy")
    ResponseEntity<String> buy(@PathVariable("trid_id") long tripId,
                               @PathVariable("user_id") long userId,
                               @RequestBody List<PersonRequest> passengers,
                               @RequestParam int count){

        if(validation.checkSeats(tripId, count ) != 1.0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Not enough seats!");
        }
        if(validation.checkSum(userId,tripId,count) != 1.0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Insufficient funds! Replenish your wallet!");
        }
        BigDecimal totalCost=tripService.calculateCost(count,tripId);
        walletService.pay(userId,totalCost);
        String list = ticketService.save(userId,tripId,count,passengers);
        senderService.buyEmail(userId,tripId,list,count);
        return ResponseEntity.status(HttpStatus.OK)
                .body("You bought " + count + " ticket(s)! Check your email!");
    }


}
