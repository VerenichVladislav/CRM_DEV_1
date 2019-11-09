package com.example.aviasales2.controller;


import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.transferObjects.CityDTO;
import com.example.aviasales2.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private ICityService cityService;

    @GetMapping
    public List<CityDTO> findAll(){
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<City> findById(@PathVariable("id") Long id){
        return cityService.findById(id);
    }

    @GetMapping("/")
    public City findByName(@RequestParam String cityName ){
        return cityService.findByCityName(cityName);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        cityService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteByCityName(@RequestParam String cityName){
        cityService.deleteByCityName(cityName);
    }

    @DeleteMapping
    public void delete(@RequestBody City city){
        cityService.delete(city);
    }

    @PostMapping
    public City save(@RequestBody City city){
        return cityService.save(city);
    }

    @PutMapping
    public void update(@RequestBody City newCity){
        cityService.update(newCity);
    }
}

