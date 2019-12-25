package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.entity.transferObjects.TourDTO;
import com.example.aviasales2.service.TourService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourService tourService;
    @Autowired
    private DozerBeanMapper mapper;
    //@Autowired
    //private TourValidator tourValidator;


    @GetMapping("/{id}")
    public TourDTO getTourById(@PathVariable("id") long id) {
        return mapper.map(tourService.findByTourId(id), TourDTO.class);
    }

    @GetMapping("/")
    public TourDTO getTourByTourName(@RequestParam String name) {
        return mapper.map(tourService.findByName(name), TourDTO.class);
    }

    @PostMapping
    public List <TourDTO> findAll() {
        return tourService.findAll().stream().map(entity -> mapper.map(entity, TourDTO.class)).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public Tour deleteTour(@PathVariable(name = "id") long id) {
        return tourService.deleteById(id);
    }

    @Transactional
    @PostMapping("/company/{companyId}/hotel/{hotelId}/city/{cityId}")
    public TourDTO save(@PathVariable(name = "companyId") long companyId,
                        @PathVariable(name = "hotelId") long hotelId,
                        @PathVariable(name = "cityId") long cityId,
                        @RequestBody @Valid TourDTO tourDTO,
                        BindingResult result) {
        // tourValidator.validate(tourDTO, result);
        if (result.hasErrors()) {
            return null;
        }
        return mapper.map(tourService.save(tourDTO, cityId, companyId, hotelId), TourDTO.class);
    }

    @PutMapping
    public TourDTO update(@RequestBody @Valid TourDTO newTour, BindingResult result) {
        // tourValidator.updateValidate(newTour, result);
        if (result.hasErrors()) {
            return null;
        }
        return mapper.map(tourService.update(newTour), TourDTO.class);
    }
}

