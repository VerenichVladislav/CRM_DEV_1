package com.example.aviasales2.controller;


import com.example.aviasales2.entity.City;
import com.example.aviasales2.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityService cityService;

    @GetMapping("/all")
    public List<City> findAll(){
        return cityService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<City> findAll(@PathVariable Long id){
        return cityService.findById(id);
    }

    @GetMapping("/name/{name}")
    public City findByName(@PathVariable(name = "name") String cityName ){
        return cityService.findByCityName(cityName);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        cityService.deleteById(id);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteByCityName(@PathVariable(name = "name") String cityName){
        cityService.deleteByCityName(cityName);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody City city){
        cityService.delete(city);
    }

    @PostMapping("/save")
    public City save(@RequestBody City city){
        return cityService.save(city);
    }

    @PutMapping("/update")
    public void update(@RequestBody City newCity){
        cityService.update(newCity);
    }
}

