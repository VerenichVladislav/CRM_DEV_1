package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.service.TourService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/tour")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/id/{id}")
    public Tour getTourById(@PathVariable long id) {
        return tourService.findByTourId(id);
    }

    @GetMapping("/name/{name}")
    public Tour getTourByTourName(@PathVariable String name) {
        return tourService.findByName(name);
    }

    @GetMapping("/All")
    public List <Tour> findAll() {
        return tourService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public Tour deleteTour(@PathVariable(name = "id") long id) {
        return tourService.deleteById(id);
    }

    @PostMapping("/save")
    public Tour save(@RequestBody Tour tour) {
        return tourService.save(tour);
    }

    @PostMapping("/update")
    private void update(@RequestBody Tour newTour) {
        tourService.save(newTour);
    }
}

