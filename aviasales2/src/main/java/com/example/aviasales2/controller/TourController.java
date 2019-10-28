package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.service.CompanyService;
import com.example.aviasales2.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourService tourService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public Tour getTourById(@PathVariable("id") long id) {
        return tourService.findByTourId(id);
    }

    @GetMapping("/")
    public Tour getTourByTourName(@RequestParam String name) {
        return tourService.findByName(name);
    }

    @GetMapping
    public List <Tour> findAll() {
        return tourService.findAll();
    }

    @DeleteMapping("/{id}")
    public Tour deleteTour(@PathVariable(name = "id") long id) {
        return tourService.deleteById(id);
    }

    @PostMapping("/company/{companyId}")
    public Tour save(@PathVariable(name = "companyId") long companyId, @RequestBody Tour tour) {
        Company company = companyService.findByCompanyId(companyId);
        tour.setCompany(company);
        company.getTours().add(tour);
        return tourService.save(tour);
    }

    @PutMapping
    public void update(@RequestBody Tour newTour) {
        tourService.save(newTour);
    }
}

