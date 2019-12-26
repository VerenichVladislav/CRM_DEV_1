package com.example.aviasales2.controller;

import com.example.aviasales2.PersonRequest;
import com.example.aviasales2.config.filterConfig.TripFilter;
import com.example.aviasales2.entity.TourRequest;
import com.example.aviasales2.entity.Trip;
import com.example.aviasales2.entity.transferObjects.SearchResultTripDto;
import com.example.aviasales2.entity.transferObjects.TripDTO;
import com.example.aviasales2.exception.GlobalBadRequestException;
import com.example.aviasales2.service.TripService;
import com.example.aviasales2.service.Validation;
import com.example.aviasales2.service.WalletService;
import com.example.aviasales2.util.TripValidator;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;
    private final WalletService walletService;
    private final Validation validation;
    private final DozerBeanMapper mapper;
    private final TripValidator tripValidator;

    @Autowired
    public TripController(TripService tripService, WalletService walletService, Validation validation, DozerBeanMapper mapper, TripValidator tripValidator) {
        this.tripService = tripService;
        this.walletService = walletService;
        this.validation = validation;
        this.mapper = mapper;
        this.tripValidator = tripValidator;
    }

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
        if (result.hasErrors()) {
            throw new GlobalBadRequestException(result);
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
            tripValidator.updateValidate(tripDTO, result);
            if (result.hasErrors()) {
                return null;
            }
            return mapper.map(tripService.update(tripDTO), TripDTO.class);
        }
        return null;
    }

    @PostMapping("/dto")
    public List <SearchResultTripDto> findAllDto(@RequestBody TripFilter tripFilter,
                                                 @RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "dateFrom") String sortBy) {
        return tripService.findAll(tripFilter, pageNo, pageSize, sortBy).stream()
                .map(entity -> mapper.map(entity, SearchResultTripDto.class))
                .collect(Collectors.toList());
    }
    @PostMapping("/cnt")
    public List <SearchResultTripDto> findAllCnt(@RequestBody TripFilter tripFilter) {
        return tripService.findAllCnt(tripFilter).stream()
                .map(entity -> mapper.map(entity, SearchResultTripDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public List <TripDTO> findAll(@RequestBody TripFilter tripFilter,
                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "dateFrom") String sortBy) {
        return tripService.findAll(tripFilter, pageNo, pageSize, sortBy).stream()
                .map(entity -> mapper.map(entity, TripDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/{user_id}/{trid_id}/buy")
    ResponseEntity <String> buy(@PathVariable("trid_id") long tripId,
                                @PathVariable("user_id") long userId,
                                @RequestBody List <PersonRequest> passengers,
                                @RequestParam int count) {

        if (validation.checkSeats(tripId, count) != 1.0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Not enough seats!");
        }
        if (validation.checkSum(userId, tripId, count) != 1.0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Insufficient funds! Replenish your wallet!");
        }
        walletService.pay(userId, tripId, count, passengers);
        return ResponseEntity.status(HttpStatus.OK)
                .body("You bought " + count + " ticket(s)! Check your email!");
    }
    @PostMapping("/byTour")
    ResponseEntity <String> buyTour(@RequestBody TourRequest tourRequest){

        try{
            tourRequest.getTripIdList().forEach(tripId->{
                if (validation.checkSeats(tripId, 1) != 1.0) {
                    throw new NullPointerException("Not enough seats!");
                }
                if (validation.checkSum(tourRequest.getUserId(), tripId, 1) != 1.0) {
                    throw new NullPointerException("Insufficient funds! Replenish your wallet!");
                }
            });
            walletService.payForTour(tourRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("You bought ticket(s)! Check your email!");
        }
        catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }


    }
}

